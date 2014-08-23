package edu.globalconflict.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
import edu.globalconflict.TheGame;
import edu.globalconflict.component.game.AttackAction;
import edu.globalconflict.component.game.EndTurnAction;
import edu.globalconflict.controller.GameController;
import edu.globalconflict.entity.Engine;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.processor.*;

/**
 * @author mateusz
 * @since 10.08.14
 */
public final class GameScreen implements Screen {
    private TheGame game;

    private Stage uiStage;
    private Label currentPlayerLabel;
    private Label availableTroopsLabel;

    private EntityManager entityManager;
    private Engine engine;
    private OrthographicCamera camera;

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
        // Game UI
        uiStage = new Stage(new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        createButtonsUI(uiStage);
        createLabelsUI(uiStage);

        // Game camera
        createCamera();

        // Register processors -- game logic
        engine = new Engine(entityManager);
        engine.registerProcessor(new AttackActionProcessor());
        engine.registerProcessor(new TransferActionProcessor());
        engine.registerProcessor(new EndTurnActionProcessor(currentPlayerLabel, availableTroopsLabel));
        engine.registerProcessor(new PlayerClickProcessor());
        engine.registerProcessor(new TerritorySelectedProcessor());
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

    private void createLabelsUI(Stage uiStage) {
        currentPlayerLabel = new Label("", MainAssets.skin);
        availableTroopsLabel = new Label("", MainAssets.skin);

        final VerticalGroup vbox = new VerticalGroup();
        vbox.addActor(currentPlayerLabel);
        vbox.addActor(availableTroopsLabel);
        vbox.setPosition(0, Constants.SCREEN_HEIGHT - 2 * 20);
        vbox.align(Align.left | Align.top);
        vbox.pad(10);
        vbox.pack();

        uiStage.addActor(vbox);
    }

    private void createButtonsUI(Stage uiStage) {
        final TextButton attackButton = new TextButton("Attack", MainAssets.skin);
        attackButton.addListener(new ActionButtonListener<>(entityManager, AttackAction.class));

        final TextButton transferButton = new TextButton("Transfer", MainAssets.skin);
        // TODO: action listener -- open pop-up, select number (validated - min/max), fire player action event

        final TextButton endTurnButton = new TextButton("End turn", MainAssets.skin);
        final EndTurnActionListener endTurnActionListener = new EndTurnActionListener(entityManager);
        endTurnButton.addListener(endTurnActionListener);

        final Table table = new Table(MainAssets.skin);
        table.add(attackButton).width(80);
        table.row();
        table.add(transferButton).width(80).padTop(5);
        table.row();
        table.add(endTurnButton).width(80).padTop(5);
        table.row();
        table.pad(5);
        table.pack();

        uiStage.addActor(table);
        uiStage.addActor(endTurnActionListener.window);
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
