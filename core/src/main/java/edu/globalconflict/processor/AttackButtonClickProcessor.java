package edu.globalconflict.processor;

import edu.globalconflict.Constants;
import edu.globalconflict.component.Player;
import edu.globalconflict.component.game.AttackAction;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.io.AttackButtonClick;
import edu.globalconflict.component.io.GameError;
import edu.globalconflict.component.io.SelectedTerritoriesStack;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.component.territory.Territory;
import edu.globalconflict.component.territory.TerritorySelected;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.UUID;

/**
 * @author mateusz
 * @since 24.08.14
 */
public final class AttackButtonClickProcessor extends EventProcessor<AttackButtonClick> implements Constants {
    public AttackButtonClickProcessor() {
        super(AttackButtonClick.class);
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, AttackButtonClick event) {
        final GameError gameError = entityManager.getComponent(gameEntity, GameError.class);

        // 1. pop two territories from stack
        final SelectedTerritoriesStack territoriesStack =
                entityManager.getComponent(gameEntity, SelectedTerritoriesStack.class);
        //   a. if not present
        if (territoriesStack.territories.size() < 2) {
            gameError.set(AT_LEAST_TWO_TERRITORIES_SELECTED);
            return;
        }

        final UUID defendingTerritoryEntity = territoriesStack.territories.pop();
        final UUID attackingTerritoryEntity = territoriesStack.territories.pop();

        // 2. unselect territories
        entityManager.getComponent(defendingTerritoryEntity, TerritorySelected.class).unselect();
        entityManager.getComponent(attackingTerritoryEntity, TerritorySelected.class).unselect();

        // 3. validate:
        //   a. are they neighbours?
        final Territory defendingTerritory = entityManager.getComponent(defendingTerritoryEntity, Territory.class);
        final Territory attackingTerritory = entityManager.getComponent(attackingTerritoryEntity, Territory.class);
        if (!attackingTerritory.isNeighbor(defendingTerritory.name)) {
            gameError.set(TERRITORIES_ARE_NOT_NEIGHBORS);
            return;
        }

        //   b. do they belong to different players?
        final Player defendingPlayer = entityManager.getComponent(defendingTerritoryEntity, Player.class);
        final Player attackingPlayer = entityManager.getComponent(attackingTerritoryEntity, Player.class);
        if (defendingPlayer.equals(attackingPlayer)) {
            gameError.set(SAME_PLAYER_TERRITORIES);
            return;
        }

        //   c. attacking player must be the same as current player
        final Player currentPlayer = entityManager.getComponent(gameEntity, CurrentPlayer.class).currentPlayer;
        if (!currentPlayer.equals(attackingPlayer)) {
            gameError.set(ATTACK_FROM_NOT_CURRENT_PLAYER);
            return;
        }

        //   d. is attacking territory not frozen?
        //   e. is attacking army greater that 1?
        final Army attackingArmy = entityManager.getComponent(attackingTerritoryEntity, Army.class);
        if (attackingArmy.frozen || attackingArmy.troops <= 1) {
            gameError.set(FROZEN_TERRITORY);
            return;
        }

        // 4. fire attack action event
        entityManager.getComponent(gameEntity, AttackAction.class)
                .set(attackingTerritoryEntity, defendingTerritoryEntity);
    }
}
