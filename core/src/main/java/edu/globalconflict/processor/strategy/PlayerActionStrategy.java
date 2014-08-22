package edu.globalconflict.processor.strategy;

import edu.globalconflict.component.game.PlayerAction;
import edu.globalconflict.entity.EntityManager;

/**
 * @author mateusz
 * @since 22.08.14
 */
public interface PlayerActionStrategy {
    void process(EntityManager entityManager, PlayerAction.Action actionEvent);
}
