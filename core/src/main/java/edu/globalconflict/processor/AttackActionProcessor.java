package edu.globalconflict.processor;

import edu.globalconflict.component.game.AttackAction;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public final class AttackActionProcessor extends EventProcessor<AttackAction> {
    public AttackActionProcessor() {
        super(AttackAction.class);
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, AttackAction event) {
        // TODO
        // 1. perform attack -- game logic
        // 2. if succeeded, change army owner, and so on
        // 3. if succeeded, freeze army -- it cannot be transferred, cannot attack till the next turn
    }
}
