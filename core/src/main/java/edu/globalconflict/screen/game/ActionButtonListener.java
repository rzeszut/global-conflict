package edu.globalconflict.screen.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import edu.globalconflict.Constants;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventComponent;
import edu.globalconflict.entity.Tag;

import java.util.UUID;

/**
 * @author mateusz
 * @since 22.08.14
 */
public class ActionButtonListener<A extends EventComponent> extends InputListener {
    private final EntityManager entityManager;
    private final Class<A> actionClass;

    public ActionButtonListener(EntityManager entityManager, Class<A> actionClass) {
        this.entityManager = entityManager;
        this.actionClass = actionClass;
    }

    @Override
    public final boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
        final A playerAction = entityManager.getComponent(gameEntity, actionClass);
        processAction(playerAction);
        return true;
    }

    /**
     * Default method for processing player action.
     * To be overridden if required.
     *
     * @param action Action event.
     */
    protected void processAction(A action) {
        action.isNew = true;
    }
}
