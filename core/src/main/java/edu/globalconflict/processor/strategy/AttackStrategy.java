package edu.globalconflict.processor.strategy;

import edu.globalconflict.component.game.PlayerAction;
import edu.globalconflict.entity.EntityManager;

/**
 * @author mateusz
 * @since 22.08.14
 */
public class AttackStrategy implements PlayerActionStrategy {
    @Override
    public void process(EntityManager entityManager, PlayerAction.Action actionEvent) {
        // 1. get two territories from stack
        // 2. validate:
        //   a. are they neighbours?
        //   b. do they belong to different players?
        //   c. is attacking territory not frozen?
        // 3. perform attack -- game logic
        // 4. if succeeded, change army owner, and so on (or add a new army if empty territory was attacked)
        // 5. if succeeded, freeze army -- it cannot be transferred, cannot attack till the next turn
    }
}
