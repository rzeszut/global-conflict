package edu.globalconflict.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.globalconflict.Constants;
import edu.globalconflict.GameAssets;
import edu.globalconflict.MainAssets;
import edu.globalconflict.TheGame;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.screen.action.CreateWorldAction;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * @author mateusz
 * @since 11.08.14
 */
public final class LoadingGameScreen implements Screen {
    private static final float FADE_DELAY = 0.25f;

    private TheGame game;
    private Stage stage;

    public LoadingGameScreen(TheGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().setWorldSize(width, height);
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

        final Label loadingLabel = new Label("Loading ...", MainAssets.skin);
        final Container<Label> container = new Container<>(loadingLabel);
        container.center();
        container.setFillParent(true);

        final EntityManager entityManager = new EntityManager();

        stage.addActor(container);
        stage.addAction(sequence(
                fadeIn(FADE_DELAY),
                run(new LoadGameAssetsAction()),
                run(new CreateWorldAction(entityManager)),
                fadeOut(FADE_DELAY),
                run(new StartGameAction(entityManager))
        ));
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private static final class LoadGameAssetsAction implements Runnable {
        @Override
        public void run() {
            GameAssets.load();
        }
    }

    private final class StartGameAction implements Runnable {
        private EntityManager entityManager;

        private StartGameAction(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        public void run() {
            game.startGame(entityManager);
        }
    }
}
