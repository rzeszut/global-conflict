package edu.globalconflict.screen.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
import edu.globalconflict.component.game.EndTurnAction;
import edu.globalconflict.entity.EntityManager;

/**
 * @author mateusz
 * @since 23.08.14
 */
public class EndTurnActionListener extends ActionButtonListener<EndTurnAction> {
    public final EndTurnWindow window = new EndTurnWindow();

    public EndTurnActionListener(EntityManager entityManager) {
        super(entityManager, EndTurnAction.class);
    }

    @Override
    protected void processAction(EndTurnAction action) {
        window.okListener.action = action;
        window.setVisible(true);
    }

    class EndTurnWindow extends Window {
        EndTurnWindow() {
            super("End Turn", MainAssets.skin, "dialog");

            final Label label = new Label("Are you sure you \n want to end your turn?", MainAssets.skin);
            label.setWrap(true);

            final TextButton okButton = new TextButton("OK", MainAssets.skin);
            okButton.addListener(okListener);
            final TextButton cancelButton = new TextButton("Cancel", MainAssets.skin);
            cancelButton.addListener(cancelListener);

            add(label).align(Align.left).row();
            add(okButton).width(80).padRight(10f);
            add(cancelButton).width(80);

            setModal(true);
            setMovable(false);
            setCenterPosition(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
            setVisible(false);
            setWidth(200);
            setHeight(100);
        }

        OkListener okListener = new OkListener();
        class OkListener extends InputListener {
            public EndTurnAction action;

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                action.isNew = true;
                EndTurnWindow.this.setVisible(false);
                return true;
            }
        }

        InputListener cancelListener = new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                EndTurnWindow.this.setVisible(false);
                return true;
            }
        };
    }
}
