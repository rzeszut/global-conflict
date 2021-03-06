package edu.globalconflict.screen.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import edu.globalconflict.Constants;
import edu.globalconflict.MainAssets;
import edu.globalconflict.component.game.TransferAction;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;

import java.util.UUID;

/**
 * @author mateusz
 * @since 24.08.14
 */
public final class TransferDialog extends Dialog {
    private Slider slider;
    private final Stage stage;
    private final EntityManager entityManager;
    private UUID selectedTerritory;

    public TransferDialog(Stage stage, EntityManager entityManager) {
        super("Transfer troops", MainAssets.skin, "dialog");

        this.stage = stage;
        this.entityManager = entityManager;

        // widgets
        final Label label = new Label("Select number of troops to transfer:", MainAssets.skin);
        slider = new Slider(0, 1, 1, false, MainAssets.skin);
        final Label currentValueLabel = new Label("0", MainAssets.skin);

        // callbacks
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                currentValueLabel.setText(String.valueOf((int) slider.getValue()));
            }
        });

        // layout setup
        getContentTable().row().colspan(2);
        getContentTable().add(label);

        getContentTable().row();
        getContentTable().add(slider).width(250);
        getContentTable().add(currentValueLabel);

        button("OK", ButtonClicked.OK).button("Cancel", ButtonClicked.CANCEL);

        setMovable(false);
        setWidth(280);
        setHeight(100);
        setCenterPosition(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
    }

    protected void result (Object object) {
        if (object == ButtonClicked.OK) {
            final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
            final TransferAction action = entityManager.getComponent(gameEntity, TransferAction.class);
            action.set(selectedTerritory, (int) slider.getValue());
        }
        hide(null);
    }

    public void show(UUID territoryEntity, int min, int max) {
        this.selectedTerritory = territoryEntity;
        this.slider.setRange(min, max);
        this.slider.setValue(0);
        this.show(this.stage, null);
    }
}
