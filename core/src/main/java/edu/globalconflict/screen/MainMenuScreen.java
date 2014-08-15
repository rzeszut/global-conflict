package edu.globalconflict.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import edu.globalconflict.MainAssets;
import edu.globalconflict.Constants;
import edu.globalconflict.TheGame;

/**
 * @author mateusz
 * @since 10.08.14
 */
public final class MainMenuScreen implements Screen {
    private TheGame game;
    private Stage stage;

    public MainMenuScreen(TheGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
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
        // TODO: test all Scaling modes
        stage = new Stage(new ScalingViewport(Scaling.none, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        // widgets
        final Table table = new Table(MainAssets.skin);

        final TextButton startGameButton = new TextButton("Start game", MainAssets.skin);
        final TextButton optionsButton = new TextButton("Options", MainAssets.skin);
        final TextButton exitButton = new TextButton("Exit", MainAssets.skin);

        // widgets config
        startGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.startLoadingGameAssets();
                return true;
            }
        });
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

        table.add(startGameButton).width(150).height(50);
        table.row();
        table.add(optionsButton).width(150).height(50).padTop(10);
        table.row();
        table.add(exitButton).width(150).height(50).padTop(10);
        table.setFillParent(true);
        stage.addActor(table);
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
        stage.dispose();
    }
}
