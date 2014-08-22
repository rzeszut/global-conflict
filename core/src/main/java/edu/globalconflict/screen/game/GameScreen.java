package edu.globalconflict.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
import edu.globalconflict.TheGame;
import edu.globalconflict.component.game.PlayerAction;
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
    private EntityManager entityManager;
    private Engine engine;

    public GameScreen(TheGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        final Table table = createGameUI();

        // current player label
        final Label currentPlayerLabel = new Label("", MainAssets.skin);
        currentPlayerLabel.setPosition(10, Constants.SCREEN_HEIGHT - 30);

        uiStage = new Stage(new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        uiStage.addActor(table);
        uiStage.addActor(currentPlayerLabel);

        // Game camera
        final OrthographicCamera camera = createCamera();

        // Register processors -- game logic
        engine = new Engine(entityManager);
        engine.registerProcessor(new PlayerActionProcessor(currentPlayerLabel));
        engine.registerProcessor(new PlayerClickProcessor());
        engine.registerProcessor(new TerritorySelectedProcessor());
        engine.registerProcessor(new TextureRenderProcessor(camera));
        if (Constants.DEBUG) {
            engine.registerProcessor(new DebugRenderProcessor(camera));
        }

        // input controller
        final GameController controller = new GameController(camera, entityManager);
        final InputProcessor inputProcessor = new InputMultiplexer(uiStage, new GestureDetector(controller));
        Gdx.input.setInputProcessor(inputProcessor);
    }

    private Table createGameUI() {
        final TextButton attackButton = new TextButton("Attack", MainAssets.skin);
        attackButton.addListener(new ActionButtonListener(entityManager, PlayerAction.ATTACK));

        final TextButton transferButton = new TextButton("Transfer", MainAssets.skin);
        // TODO: action listener -- open pop-up, select number (validated - min/max), fire player action event

        final TextButton endTurnButton = new TextButton("End turn", MainAssets.skin);
        endTurnButton.addListener(new ActionButtonListener(entityManager, PlayerAction.END_TURN));

        final Table table = new Table(MainAssets.skin);
        table.add(attackButton).width(80);
        table.row();
        table.add(transferButton).width(80).padTop(5);
        table.row();
        table.add(endTurnButton).width(80).padTop(5);
        table.row();
        table.pad(5);
        table.pack();

        return table;
    }

    private OrthographicCamera createCamera() {
        final OrthographicCamera camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.translate((Constants.WORLD_WIDTH - Constants.SCREEN_WIDTH) * 0.5f,
                (Constants.WORLD_HEIGHT - Constants.SCREEN_HEIGHT) * 0.5f);
        camera.update();
        return camera;
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
