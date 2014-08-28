package edu.globalconflict.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
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
//        stage.getViewport().setWorldSize(width, height);
    }

    @Override
    public void show() {
        // TODO: test all Scaling modes
        stage = new Stage(new ScalingViewport(Scaling.none, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        // widgets
        final TextButton startGameButton = new TextButton("Start game", MainAssets.skin);
        final TextButton exitButton = new TextButton("Exit", MainAssets.skin);

        // callbacks
        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToPlayerSelection();
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // layout
        final Table table = new Table(MainAssets.skin);
        table.add(startGameButton).width(150).height(50);
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
