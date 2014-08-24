package edu.globalconflict.processor;

import edu.globalconflict.Constants;
import edu.globalconflict.component.Player;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.io.GameError;
import edu.globalconflict.component.io.SelectedTerritoriesStack;
import edu.globalconflict.component.io.TransferButtonClick;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.component.territory.TerritorySelected;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;
import edu.globalconflict.screen.game.TransferDialog;

import java.util.UUID;

/**
 * @author mateusz
 * @since 24.08.14
 */
public final class TransferButtonClickProcessor extends EventProcessor<TransferButtonClick> {
    private final TransferDialog dialog;

    public TransferButtonClickProcessor(TransferDialog dialog) {
        super(TransferButtonClick.class);
        this.dialog = dialog;
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, TransferButtonClick event) {
        final GameError gameError = entityManager.getComponent(gameEntity, GameError.class);

        // 1. get one territory from stack
        final SelectedTerritoriesStack territoriesStack =
                entityManager.getComponent(gameEntity, SelectedTerritoriesStack.class);
        //   a. if not present
        if (territoriesStack.territories.size() < 1) {
            gameError.set(Constants.AT_LEAST_ONE_TERRITORY_SELECTED);
            return;
        }
        final UUID selectedTerritory = territoriesStack.territories.pop();

        // 2. unselect territory
        entityManager.getComponent(selectedTerritory, TerritorySelected.class).unselect();

        // 3. get current player
        final CurrentPlayer currentPlayer = entityManager.getComponent(gameEntity, CurrentPlayer.class);

        // 4. validate:
        //   a. if territory (army, actually) is frozen
        //   b. same player as current player
        final Army army = entityManager.getComponent(selectedTerritory, Army.class);
        final Player territoryOwner = entityManager.getComponent(selectedTerritory, Player.class);

        if (army.frozen || !currentPlayer.currentPlayer.equals(territoryOwner)) {
            gameError.set(Constants.TRANSFER_FAILED);
            return;
        }

        // 5. calculate min/max transfer amount
        final int min = -(army.troops - 1);
        final int max = currentPlayer.currentPlayer.availableTroops;

        // 6. show transfer dialog
        dialog.show(selectedTerritory, min, max);
    }
}
