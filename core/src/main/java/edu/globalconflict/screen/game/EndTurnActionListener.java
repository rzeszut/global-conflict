package edu.globalconflict.screen.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import edu.globalconflict.MainAssets;
import edu.globalconflict.component.game.EndTurnAction;
import edu.globalconflict.entity.EntityManager;


/**
 * @author mateusz
 * @since 23.08.14
 */
public final class EndTurnActionListener extends ActionButtonListener<EndTurnAction> {
    private final EndTurnWindow window = new EndTurnWindow();
    private final Stage stage;

    public EndTurnActionListener(EntityManager entityManager, Stage stage) {
        super(entityManager, EndTurnAction.class);
        this.stage = stage;
    }

    @Override
    protected void processAction(EndTurnAction action) {
        window.action = action;
        window.show(stage);
    }

    class EndTurnWindow extends Dialog {
        EndTurnAction action;

        EndTurnWindow() {
            super("End Turn", MainAssets.skin, "dialog");

            text("Are you sure you\nwant to end your turn?");
            button("OK", ButtonClicked.OK).button("Cancel", ButtonClicked.CANCEL);

            setMovable(false);
            setWidth(200);
            setHeight(100);
        }

        protected void result (Object object) {
            if (object == ButtonClicked.OK) {
                action.isNew = true;
            }
            hide();
        }
    }
}
