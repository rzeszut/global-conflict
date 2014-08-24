package edu.globalconflict.screen.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;

/**
 * @author mateusz
 * @since 24.08.14
 */
public final class ErrorDialog extends Dialog {
    private final Stage stage;
    private final Label errorMessage;

    public ErrorDialog(Stage stage) {
        super("Game error", MainAssets.skin, "dialog");

        this.stage = stage;
        errorMessage = new Label("", MainAssets.skin);

        text(errorMessage);
        button("OK");

        setMovable(false);
        setWidth(300);
        setHeight(100);
        setCenterPosition(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
    }

    protected void result (Object object) {
        hide(null);
    }

    public void show(String errorMessage) {
        this.errorMessage.setText(errorMessage);
        show(stage, null);
    }
}
