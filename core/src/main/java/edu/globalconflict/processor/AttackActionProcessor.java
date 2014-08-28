package edu.globalconflict.processor;

import edu.globalconflict.component.Player;
import edu.globalconflict.component.TintColor;
import edu.globalconflict.component.game.AttackAction;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.Random;
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
            final int attackerRoll = rnd.nextInt(6);

            // decide who won this fight and kill some soldiers
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
            final Player winner = entityManager.getComponent(event.attackingTerritory, Player.class);
            entityManager.addComponent(event.defendingTerritory, winner);
            entityManager.getComponent(event.defendingTerritory, TintColor.class).color.set(winner.color);

            // freeze newly won territory
            defendingArmy.frozen = true;
        }

        // if defender won
        else if (attackingArmy.troops == 0) {
            // don not transfer troops -- if defender won, he leaves only 1 soldier on the newly conquered territory
            attackingArmy.troops = 1;
            defendingArmy.troops = Math.max(1, defendingArmy.troops - 1);

            // transfer ownership of the territory, set color
            final Player winner = entityManager.getComponent(event.defendingTerritory, Player.class);
            entityManager.addComponent(event.attackingTerritory, winner);
            entityManager.getComponent(event.attackingTerritory, TintColor.class).color.set(winner.color);
        }
    }
}
