package edu.globalconflict.processor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.globalconflict.Constants;
import edu.globalconflict.component.game.PlayerAction;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Processor;
import edu.globalconflict.entity.Tag;
import edu.globalconflict.processor.strategy.AttackStrategy;
import edu.globalconflict.processor.strategy.EndTurnStrategy;
import edu.globalconflict.processor.strategy.PlayerActionStrategy;
import edu.globalconflict.processor.strategy.TransferStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author mateusz
 * @since 22.08.14
 */
public final class PlayerActionProcessor implements Processor {
    private Map<PlayerAction.Action, PlayerActionStrategy> strategyMap;

    public PlayerActionProcessor(Label currentPlayerLabel) {
        strategyMap = new HashMap<>(3);
        strategyMap.put(PlayerAction.ATTACK, new AttackStrategy());
        strategyMap.put(PlayerAction.TRANSFER, new TransferStrategy());
        strategyMap.put(PlayerAction.END_TURN, new EndTurnStrategy(currentPlayerLabel));
    }

    @Override
    public void process(EntityManager entityManager, float delta) {
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
        final PlayerAction playerAction = entityManager.getComponent(gameEntity, PlayerAction.class);

        if (playerAction.isNew) {
            // process
            final PlayerActionStrategy actionStrategy = strategyMap.get(playerAction.action);
            actionStrategy.process(entityManager, playerAction.action);

            // action processed
            playerAction.isNew = false;
        }
    }
}
