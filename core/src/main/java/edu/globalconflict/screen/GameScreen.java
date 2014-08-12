package edu.globalconflict.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import edu.globalconflict.Constants;
import edu.globalconflict.TheGame;
import edu.globalconflict.controller.OrthographicCameraController;
import edu.globalconflict.model.World;
import edu.globalconflict.view.GameRenderer;

/**
 * @author mateusz
 * @since 10.08.14
 */
public final class GameScreen implements Screen {
    private TheGame game;

    private GameRenderer renderer;

    public GameScreen(TheGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f, 0.9f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        final OrthographicCamera camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.translate(0, Constants.WORLD_HEIGHT - Constants.SCREEN_HEIGHT);
        camera.update();

        renderer = new GameRenderer(camera, new World());

        final GestureDetector gestureDetector = new GestureDetector(new OrthographicCameraController(camera));
        Gdx.input.setInputProcessor(gestureDetector);
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
    }
}
