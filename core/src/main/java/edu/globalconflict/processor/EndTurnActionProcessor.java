package edu.globalconflict.processor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.globalconflict.component.Player;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.game.EndTurnAction;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public final class EndTurnActionProcessor extends EventProcessor<EndTurnAction> {
    private final Label currentPlayerLabel;
    private final Label availableTroopsLabel;

    public EndTurnActionProcessor(Label currentPlayerLabel, Label availableTroopsLabel) {
        super(EndTurnAction.class);
        this.currentPlayerLabel = currentPlayerLabel;
        this.availableTroopsLabel = availableTroopsLabel;
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, EndTurnAction event) {
        // 1. change to next player
        final CurrentPlayer currentPlayer = entityManager.getComponent(gameEntity, CurrentPlayer.class);
        currentPlayer.nextPlayer();

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
        //   b. TODO: completed continents

        currentPlayer.currentPlayer.availableTroops += troopsSpawn;

        // 5. update available troops label
        availableTroopsLabel.setColor(currentPlayer.currentPlayer.color);
        availableTroopsLabel.setText("Available troops: " + currentPlayer.currentPlayer.availableTroops);

        // 6. TODO: clear selected territories
    }
}
