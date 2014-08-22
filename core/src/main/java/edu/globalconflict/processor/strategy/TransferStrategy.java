package edu.globalconflict.processor.strategy;

import edu.globalconflict.component.game.PlayerAction;
import edu.globalconflict.entity.EntityManager;

/**
 * @author mateusz
 * @since 22.08.14
 */
public class TransferStrategy implements PlayerActionStrategy {
    @Override
    public void process(EntityManager entityManager, PlayerAction.Action actionEvent) {
        // 1. get one territory from stack
        // 2. get current player
        // 3. validate:
        //   a. transfer amount
        //   b. if territory (army, actually) is frozen
        //   c. same player as current player
        // 4. perform transfer -- game logic
        // 5. update available troops label
    }
}
