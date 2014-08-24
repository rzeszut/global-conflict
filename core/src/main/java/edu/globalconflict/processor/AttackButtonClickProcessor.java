package edu.globalconflict.processor;

import edu.globalconflict.component.io.AttackButtonClick;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.UUID;

/**
 * @author mateusz
 * @since 24.08.14
 */
public final class AttackButtonClickProcessor extends EventProcessor<AttackButtonClick> {
    public AttackButtonClickProcessor() {
        super(AttackButtonClick.class);
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, AttackButtonClick event) {
        // TODO
        // 1. pop two territories from stack
        // 2. unselect territories
        // 3. validate:
        //   a. are they neighbours?
        //   b. do they belong to different players?
        //   c. is attacking territory not frozen?
        // 4. fire attack action event
    }
}
