package edu.globalconflict.processor;

import edu.globalconflict.component.Player;
import edu.globalconflict.component.TintColor;
import edu.globalconflict.component.game.AttackAction;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.game.GameWon;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public final class AttackActionProcessor extends EventProcessor<AttackAction> {
    private final Random rnd = new Random();

    public AttackActionProcessor() {
        super(AttackAction.class);
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, AttackAction event) {
        // get armies for both territories
        final Army defendingArmy = entityManager.getComponent(event.defendingTerritory, Army.class);
        final Army attackingArmy = entityManager.getComponent(event.attackingTerritory, Army.class);

        // for each attacking troop
        final int rounds = attackingArmy.troops;
        for (int i = 0; i < rounds; ++i) {
            // if either side lost, break the loop
            if (attackingArmy.troops == 0 || defendingArmy.troops == 0) {
                break;
            }

            // each side rolls a dice
            final int defenderRoll = rnd.nextInt(6);
            // attacker chooses best one of two rolls
            final int attackerRoll = Math.max(rnd.nextInt(6), rnd.nextInt(6));

            // decide who won this fight and kill some soldiers
            // defender wins on equal rolls
            if (attackerRoll > defenderRoll) {
                --defendingArmy.troops;
            } else {
                --attackingArmy.troops;
            }
        }

        // if attacker won
        if (defendingArmy.troops == 0) {
            // transfer troops to the new territory
            defendingArmy.troops = Math.max(1, attackingArmy.troops - 1);
            attackingArmy.troops = 1;

            // transfer ownership of the territory, set color
            changeOwner(entityManager, event.attackingTerritory, event.defendingTerritory);

            //check win condition
            final CurrentPlayer currentPlayer = entityManager.getComponent(gameEntity, CurrentPlayer.class);

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

                if(playerTerritories == 42) {
                    entityManager.getComponent(gameEntity, GameWon.class).isNew = true;
                    return;
                }

            }

            // freeze newly won territory
            defendingArmy.frozen = true;
        }

        // if defender won
        else if (attackingArmy.troops == 0) {
            // don not transfer troops -- if defender won, he leaves only 1 soldier on the newly conquered territory
            attackingArmy.troops = 1;
            defendingArmy.troops = Math.max(1, defendingArmy.troops - 1);

            // transfer ownership of the territory, set color
            changeOwner(entityManager, event.defendingTerritory, event.attackingTerritory);
        }
    }

    private void changeOwner(EntityManager entityManager, UUID winnerTerritory, UUID loserTerritory) {
        final Player winner = entityManager.getComponent(winnerTerritory, Player.class);
        entityManager.addComponent(loserTerritory, winner);
        entityManager.getComponent(loserTerritory, TintColor.class).color.set(winner.color);
    }
}
