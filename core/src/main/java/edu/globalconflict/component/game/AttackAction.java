package edu.globalconflict.component.game;

import edu.globalconflict.entity.EventComponent;

import java.util.UUID;

/**
 * Component representing player 'Attack' action.
 *
 * @author mateusz
 * @since 23.08.14
 */
public final class AttackAction extends EventComponent {
    /**
     * ID of the attacking territory.
     */
    public UUID attackingTerritory;
    /**
     * ID of the defending territory.
     */
    public UUID defendingTerritory;

    public void set(UUID attackingTerritory, UUID defendingTerritory) {
        this.attackingTerritory = attackingTerritory;
        this.defendingTerritory = defendingTerritory;
        this.isNew = true;
    }
}
