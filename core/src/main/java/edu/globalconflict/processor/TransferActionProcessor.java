package edu.globalconflict.processor;

import edu.globalconflict.component.game.TransferAction;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public class TransferActionProcessor extends EventProcessor<TransferAction> {
    public TransferActionProcessor() {
        super(TransferAction.class);
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, TransferAction event) {
        System.out.println("Transferred " + event.transferredTroops + " troops");
        // 1. get one territory from stack
        // 2. get current player
        // 3. validate:
        //   a. transfer amount
        //   b. if territory (army, actually) is frozen
        //      * TODO: should freezing validation actually happen here? or in UI?
        //   c. same player as current player
        // 4. perform transfer -- game logic
        // 5. update available troops label
        // 6. finally -- clear selected territories stack
    }
}
