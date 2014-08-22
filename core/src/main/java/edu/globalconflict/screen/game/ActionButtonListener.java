package edu.globalconflict.screen.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import edu.globalconflict.Constants;
import edu.globalconflict.component.game.PlayerAction;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;

import java.util.UUID;

/**
 * @author mateusz
 * @since 22.08.14
 */
public final class ActionButtonListener extends InputListener {
    private final EntityManager entityManager;
    private final PlayerAction.Action action;

    public ActionButtonListener(EntityManager entityManager, PlayerAction.Action action) {
        this.entityManager = entityManager;
        this.action = action;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
        final PlayerAction playerAction = entityManager.getComponent(gameEntity, PlayerAction.class);
        playerAction.set(action);
        return true;
    }
}
