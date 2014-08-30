package edu.globalconflict.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
import edu.globalconflict.TheGame;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mateusz
 * @since 28.08.14
 */
public final class SelectPlayersScreen implements Screen {
    private static final Integer[] SELECT_OPTIONS = new Integer[]{2, 3, 4, 5, 6};
    private static final Integer DEFAULT_SELECTED = 6;

    private TheGame game;
    private Stage stage;

    private SelectBox<Integer> playerCountSelect;
    private TextField[] playerNameFields;

    public SelectPlayersScreen(TheGame game) {
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
        playerCountSelect = new SelectBox<>(MainAssets.skin);
        playerCountSelect.setItems(SELECT_OPTIONS);
        playerCountSelect.setSelected(DEFAULT_SELECTED);

        playerNameFields = new TextField[]{
                createTextField("Player 1"),
                createTextField("Player 2"),
                createTextField("Player 3"),
                createTextField("Player 4"),
                createTextField("Player 5"),
                createTextField("Player 6")
        };

        final TextButton okButton = new TextButton("OK", MainAssets.skin);
        final TextButton cancelButton = new TextButton("Cancel", MainAssets.skin);

        // callbacks
        playerCountSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final int numberOfPlayers = playerCountSelect.getSelected();

                int i = 0;
                for (; i < numberOfPlayers; ++i) {
                    playerNameFields[i].setVisible(true);
                }
                for (; i < playerNameFields.length; ++i) {
                    playerNameFields[i].setVisible(false);
                }
            }
        });

        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToLoadingNewGame(getPlayerNames());
            }
        });
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToMainMenu();
            }
        });

        // layout
        final Table table = new Table(MainAssets.skin);
        table.row().padBottom(20);
        table.add("Select number of players");
        table.add(playerCountSelect);

        for (TextField nameField : playerNameFields) {
            table.row().padBottom(10).align(Align.center);
            table.add(nameField).width(400).colspan(2);
        }

        // additional padding
        table.row().padTop(10);
        table.add(okButton).width(100);
        table.add(cancelButton).width(100);

        table.setFillParent(true);
        stage.addActor(table);
    }

    private TextField createTextField(String playerNamePlaceholder) {
        final TextField nameField = new TextField(playerNamePlaceholder, MainAssets.skin);
        nameField.setTextFieldListener(nameFieldListener);
        return nameField;
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

    private List<String> getPlayerNames() {
        final int numberOfPlayers = playerCountSelect.getSelected();
        final List<String> result = new ArrayList<>(numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; ++i) {
            result.add(playerNameFields[i].getText());
        }

        return result;
    }

    private final TextField.TextFieldListener nameFieldListener = new TextField.TextFieldListener() {
        @Override
        public void keyTyped(TextField textField, char c) {
            if (c == '\n') {
                textField.getOnscreenKeyboard().show(false);
            }
        }
    };
}
