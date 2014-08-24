package edu.globalconflict.screen.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.game.SelectedTerritoriesStack;
import edu.globalconflict.component.game.TransferAction;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;

import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public final class TransferActionListener extends ActionButtonListener<TransferAction> {
    private final TransferWindow window = new TransferWindow();
    private final Stage stage;

    public TransferActionListener(EntityManager entityManager, Stage stage) {
        super(entityManager, TransferAction.class);
        this.stage = stage;
    }

    protected void processAction(TransferAction action) {
        // 1. get current player
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
        final CurrentPlayer currentPlayer = entityManager.getComponent(gameEntity, CurrentPlayer.class);

        // 2. get latest territory clicked
        final SelectedTerritoriesStack territoriesStack =
                entityManager.getComponent(gameEntity, SelectedTerritoriesStack.class);
        //   a. if not present, return quietly
        if (territoriesStack.territories.size() < 1) {
            return;
        }
        final UUID selectedTerritory = territoriesStack.territories.peek();

        // 3. calculate min/max for slider
        final Army army = entityManager.getComponent(selectedTerritory, Army.class);
        final int min = -(army.troops - 1);
        final int max = currentPlayer.currentPlayer.availableTroops;

        // 4. show window
        window.slider.setRange(min, max);
        window.slider.setValue(0);
        window.action = action;
        window.show(stage);
    }

    class TransferWindow extends Dialog {
        Slider slider;
        TransferAction action;

        public TransferWindow() {
            super("Transfer troops", MainAssets.skin, "dialog");

            final Label label = new Label("Select number of troops to transfer:", MainAssets.skin);
            slider = new Slider(0, 1, 1, false, MainAssets.skin);

            getContentTable().add(label).row();
            getContentTable().add(slider).width(250);

            button("OK", ButtonClicked.OK).button("Cancel", ButtonClicked.CANCEL);

            setMovable(false);
            setWidth(280);
            setHeight(100);
        }

        protected void result (Object object) {
            if (object == ButtonClicked.OK) {
                action.isNew = true;
                action.transferredTroops = (int) slider.getValue();
            }
            hide();
        }
    }
}
