package edu.globalconflict.processor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.globalconflict.component.Continent;
import edu.globalconflict.component.Player;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.game.EndTurnAction;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.component.territory.TerritorySelected;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;
import edu.globalconflict.entity.Tag;
import edu.globalconflict.screen.game.AvailableTroopsLabel;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public final class EndTurnActionProcessor extends EventProcessor<EndTurnAction> {
    private final Label currentPlayerLabel;
    private final AvailableTroopsLabel availableTroopsLabel;

    public EndTurnActionProcessor(Label currentPlayerLabel, AvailableTroopsLabel availableTroopsLabel) {
        super(EndTurnAction.class);
        this.currentPlayerLabel = currentPlayerLabel;
        this.availableTroopsLabel = availableTroopsLabel;
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, EndTurnAction event) {
        // 1. change to next player
        final CurrentPlayer currentPlayer = entityManager.getComponent(gameEntity, CurrentPlayer.class);
        currentPlayer.nextPlayer();

        // TODO: win/lose conditions

        // 2. update current player label
        currentPlayerLabel.setColor(currentPlayer.currentPlayer.color);
        currentPlayerLabel.setText(currentPlayer.currentPlayer.name);

        int playerTerritories = 0;
        final Set<Map.Entry<UUID, Army>> armedEntries =
                entityManager.getEntitiesWithComponentsForType(Army.class);

        for (Map.Entry<UUID, Army> entry : armedEntries) {
            final UUID territoryEntity = entry.getKey();
            final Army army = entry.getValue();
            final Player owner = entityManager.getComponent(territoryEntity, Player.class);

            // 3. unfreeze player territories (armies, actually)
            if (currentPlayer.currentPlayer.equals(owner)) {
                army.frozen = false;
                ++playerTerritories;
            }
        }

        // 4. calculate available troops for player
        //   a. territories
        int troopsSpawn = Math.max(3, playerTerritories / 3);

        //   b. completed continents
        final Collection<Continent> continents = entityManager.getComponentsForType(Continent.class);
        for (Continent continent : continents) {
            // if all territories in the continent are owned by current player, add bonus
            if (isContinentOwnedByCurrentPlayer(entityManager, currentPlayer, continent)) {
                troopsSpawn += continent.bonus;
            }
        }

        currentPlayer.currentPlayer.availableTroops += troopsSpawn;

        // 5. update available troops label
        availableTroopsLabel.update(currentPlayer);

        // 6. clear selected territories
        final Collection<TerritorySelected> selectedTerritories =
                entityManager.getComponentsForType(TerritorySelected.class);
        for (TerritorySelected selectedTerritory : selectedTerritories) {
            selectedTerritory.unselect();
        }
    }

    private static boolean isContinentOwnedByCurrentPlayer(EntityManager entityManager, CurrentPlayer currentPlayer, Continent continent) {
        // loop over all territories in a continent
        for (String territoryName : continent.territories) {
            final UUID territoryEntity = entityManager.getEntityForTag(Tag.Namespace.TERRITORY, territoryName);
            final Player owner = entityManager.getComponent(territoryEntity, Player.class);

            // if the owner isn't the current player, no bonus fo continent
            if (!owner.equals(currentPlayer.currentPlayer)) {
                return false;
            }
        }
        return true;
    }
}
