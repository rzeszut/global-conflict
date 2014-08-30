package edu.globalconflict.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
import edu.globalconflict.TheGame;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.serializer.GameSerializer;

/**
 * @author mateusz
 * @since 30.08.14
 */
public final class PauseScreen implements Screen {
    private TheGame game;
    private Stage stage;

    private EntityManager entityManager;

    public PauseScreen(TheGame game) {
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
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        // widgets
        final TextButton resumeButton = new TextButton("Resume game", MainAssets.skin);
        final TextButton saveButton = new TextButton("Save game", MainAssets.skin);
        final TextButton exitButton = new TextButton("Exit to main menu", MainAssets.skin);
        final InfoDialog dialog = new InfoDialog();

        // callbacks
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.resumeGame();
            }
        });
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSerializer.save(entityManager, "savefile.json");
                dialog.show(stage, null);
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToMainMenu();
            }
        });

        // layout
        final Table table = new Table(MainAssets.skin);
        table.add(resumeButton).width(200).height(50);
        table.row();
        table.add(saveButton).width(200).height(50).padTop(10);
        table.row();
        table.add(exitButton).width(200).height(50).padTop(10);
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

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    static class InfoDialog extends Dialog {
        public InfoDialog() {
            super("Save", MainAssets.skin, "dialog");

            text("Game saved successfully");
            button("OK");

            setMovable(false);
            setWidth(200);
            setHeight(100);
            setCenterPosition(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        }

        protected void result (Object object) {
            hide(null);
        }
    }
}
