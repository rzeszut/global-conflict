package edu.globalconflict.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
import edu.globalconflict.TheGame;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.io.AttackButtonClick;
import edu.globalconflict.component.io.TransferButtonClick;
import edu.globalconflict.controller.GameController;
import edu.globalconflict.entity.Engine;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;
import edu.globalconflict.processor.*;
import edu.globalconflict.util.Ortho2DCamera;

import java.util.UUID;

/**
 * @author mateusz
 * @since 10.08.14
 */
public final class GameScreen implements Screen {
    private TheGame game;
    private Stage uiStage;
    private Label currentPlayerLabel;
    private AvailableTroopsLabel availableTroopsLabel;
    private InputProcessor inputProcessor;

    private EntityManager entityManager;
    private Engine engine;
    private Ortho2DCamera camera;

    public GameScreen(TheGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        // update UI
        uiStage.act(delta);

        // run every processor
        engine.update(delta);

        // draw UI on top
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        uiStage.dispose();
    }

    public void initialize(EntityManager entityManager) {
        this.entityManager = entityManager;

        // Game UI
        uiStage = new Stage(new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        createButtonsUI();
        createLabelsUI();
        createMenuUI();

        final TransferDialog transferDialog = new TransferDialog(uiStage, entityManager);
        final ErrorDialog errorDialog = new ErrorDialog(uiStage);

        // Game camera
        createCamera();

        // Register processors -- game logic
        engine = new Engine(entityManager);
        // player input/output
        engine.registerProcessor(new AttackButtonClickProcessor());
        engine.registerProcessor(new TransferButtonClickProcessor(transferDialog));
        engine.registerProcessor(new PlayerClickProcessor());
        engine.registerProcessor(new GameErrorProcessor(errorDialog));
        engine.registerProcessor(new TerritorySelectedProcessor());

        // game logic
        engine.registerProcessor(new AttackActionProcessor());
        engine.registerProcessor(new TransferActionProcessor(availableTroopsLabel));
        engine.registerProcessor(new EndTurnActionProcessor(currentPlayerLabel, availableTroopsLabel));

        // rendering
        engine.registerProcessor(new TextureRenderProcessor(camera));
        engine.registerProcessor(new ArmyRenderProcessor(camera));
        if (Constants.DEBUG) {
            engine.registerProcessor(new DebugRenderProcessor(camera));
        }

        // input controller
        final GameController controller = new GameController(camera, entityManager);
        inputProcessor = new InputMultiplexer(uiStage, controller);
    }

    private void createLabelsUI() {
        currentPlayerLabel = new Label("", MainAssets.skin);
        availableTroopsLabel = new AvailableTroopsLabel();

        // update labels with valid values, if possible
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
        final CurrentPlayer currentPlayer = entityManager.getComponent(gameEntity, CurrentPlayer.class);
        if (currentPlayer.currentPlayer != null) {
            currentPlayerLabel.setColor(currentPlayer.currentPlayer.color);
            currentPlayerLabel.setText(currentPlayer.currentPlayer.name);
            availableTroopsLabel.update(currentPlayer);
        }

        final Table table = new Table(MainAssets.skin);
        table.add(currentPlayerLabel).width(200).height(20);
        table.row();
        table.add(availableTroopsLabel).width(200).height(20);
        table.row();
        table.setPosition(0, Constants.SCREEN_HEIGHT - 2 * 20 - 20);
        table.pad(10);
        table.pack();
        table.setBackground("tableBg");

        uiStage.addActor(table);
    }

    private void createButtonsUI() {
        final TextButton attackButton = new TextButton("Attack", MainAssets.skin);
        attackButton.addListener(new ActionButtonListener<>(entityManager, AttackButtonClick.class));

        final TextButton transferButton = new TextButton("Transfer", MainAssets.skin);
        transferButton.addListener(new ActionButtonListener<>(entityManager, TransferButtonClick.class));

        final TextButton endTurnButton = new TextButton("End turn", MainAssets.skin);
        endTurnButton.addListener(new EndTurnActionListener(entityManager, uiStage));

        final Table table = new Table(MainAssets.skin);
        table.add(attackButton).width(100);
        table.row();
        table.add(transferButton).width(100).padTop(5);
        table.row();
        table.add(endTurnButton).width(100).padTop(5);
        table.row();
        table.pad(5);
        table.pack();
        table.setBackground("tableBg");

        uiStage.addActor(table);
    }

    private void createMenuUI() {
        final TextButton button = new TextButton("X", MainAssets.skin);
        button.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.pauseGame(entityManager);
            }
        });

        final Table table = new Table(MainAssets.skin);
        table.row().pad(10);
        table.add(button).width(30).height(30);
        table.setPosition(Constants.SCREEN_WIDTH - 50, Constants.SCREEN_HEIGHT - 50);
        table.pack();
        table.setBackground("tableBg");

        uiStage.addActor(table);
    }

    private void createCamera() {
        camera = new Ortho2DCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.setAreaLimit(0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        camera.setZoomLimit(0.5f, 2.5f);

        // default zoom and position
        camera.zoom = 1.5f;
        camera.translate((Constants.WORLD_WIDTH - Constants.SCREEN_WIDTH) * 0.5f,
                (Constants.WORLD_HEIGHT - Constants.SCREEN_HEIGHT) * 0.5f);

        camera.update();
    }
}
