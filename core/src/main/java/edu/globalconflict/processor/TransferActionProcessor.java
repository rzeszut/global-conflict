package edu.globalconflict.processor;

import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.game.TransferAction;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;
import edu.globalconflict.screen.game.AvailableTroopsLabel;

import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public final class TransferActionProcessor extends EventProcessor<TransferAction> {
    public final AvailableTroopsLabel availableTroopsLabel;

    public TransferActionProcessor(AvailableTroopsLabel availableTroopsLabel) {
        super(TransferAction.class);
        this.availableTroopsLabel = availableTroopsLabel;
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, TransferAction event) {
        // 1. perform transfer -- game logic
        final CurrentPlayer currentPlayer = entityManager.getComponent(gameEntity, CurrentPlayer.class);
        final Army army = entityManager.getComponent(event.territoryEntity, Army.class);
        // those values are assumed to be correct -- gui won't allow input of invalid values
        army.troops += event.transferredTroops;
        currentPlayer.currentPlayer.availableTroops -= event.transferredTroops;

        // 2. update available troops label
        availableTroopsLabel.update(currentPlayer);
    }
}
