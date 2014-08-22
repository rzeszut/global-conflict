package edu.globalconflict.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.globalconflict.Constants;
import edu.globalconflict.TheGame;
import edu.globalconflict.controller.GameController;
import edu.globalconflict.entity.Engine;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.processor.DebugRenderProcessor;
import edu.globalconflict.processor.PlayerClickProcessor;
import edu.globalconflict.processor.TextureRenderProcessor;
import edu.globalconflict.processor.TerritorySelectedProcessor;

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

        uiStage.act(delta);
        uiStage.draw();

        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        uiStage = new Stage();

        final OrthographicCamera camera = createCamera();

        engine = new Engine(entityManager);
        engine.registerProcessor(new PlayerClickProcessor());
        engine.registerProcessor(new TerritorySelectedProcessor());
        engine.registerProcessor(new TextureRenderProcessor(camera));
        if (Constants.DEBUG) {
            engine.registerProcessor(new DebugRenderProcessor(camera));
        }

        final GameController controller = new GameController(camera, entityManager);
        Gdx.input.setInputProcessor(new GestureDetector(controller));
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
