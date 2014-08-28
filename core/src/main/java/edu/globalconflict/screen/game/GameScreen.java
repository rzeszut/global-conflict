package edu.globalconflict.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
import edu.globalconflict.component.io.AttackButtonClick;
import edu.globalconflict.component.io.TransferButtonClick;
import edu.globalconflict.controller.GameController;
import edu.globalconflict.entity.Engine;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.processor.*;

/**
 * @author mateusz
 * @since 10.08.14
 */
public final class GameScreen implements Screen {
    private Stage uiStage;
    private Label currentPlayerLabel;
    private Label availableTroopsLabel;

    private EntityManager entityManager;
    private Engine engine;
    private OrthographicCamera camera;

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
        // Game UI
        uiStage = new Stage(new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        createButtonsUI();
        createLabelsUI();

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
        final InputProcessor inputProcessor = new InputMultiplexer(uiStage, controller);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    private void createLabelsUI() {
        currentPlayerLabel = new Label("", MainAssets.skin);
        availableTroopsLabel = new Label("", MainAssets.skin);

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

    private void createCamera() {
        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.translate((Constants.WORLD_WIDTH - Constants.SCREEN_WIDTH) * 0.5f,
                (Constants.WORLD_HEIGHT - Constants.SCREEN_HEIGHT) * 0.5f);
        camera.update();
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

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
