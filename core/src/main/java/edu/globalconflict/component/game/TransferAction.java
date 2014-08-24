package edu.globalconflict.component.game;

import edu.globalconflict.entity.EventComponent;

import java.util.UUID;

/**
 * Component representing player 'Transfer troops' action.
 *
 * @author mateusz
 * @since 23.08.14
 */
public final class TransferAction extends EventComponent {
    /**
     * Number of transferred troops.
     */
    public int transferredTroops = 0;
    /**
     * ID of the territory to transfer.
     */
    public UUID territoryEntity;

    public void set(UUID territoryEntity, int troops) {
        this.territoryEntity = territoryEntity;
        this.transferredTroops = troops;
        this.isNew = true;
    }
}
