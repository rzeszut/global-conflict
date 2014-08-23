package edu.globalconflict.processor;

import edu.globalconflict.component.game.AttackAction;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public class AttackActionProcessor extends EventProcessor<AttackAction> {
    public AttackActionProcessor() {
        super(AttackAction.class);
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, AttackAction event) {
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
