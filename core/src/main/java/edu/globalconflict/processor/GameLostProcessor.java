package edu.globalconflict.processor;

import edu.globalconflict.Constants;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.game.EndTurnAction;
import edu.globalconflict.component.game.GameLost;
import edu.globalconflict.component.io.GameError;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.UUID;

/**
 * @author mateusz
 * @since 31.08.14
 */
public class GameLostProcessor extends EventProcessor<GameLost> {
    public GameLostProcessor() {
        super(GameLost.class);
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, GameLost event) {
        // remove current player
        entityManager.getComponent(gameEntity, CurrentPlayer.class).playerIterator.remove();

        // show error message
        entityManager.getComponent(gameEntity, GameError.class).set(Constants.GAME_LOST);

        // end turn event
        entityManager.getComponent(gameEntity, EndTurnAction.class).isNew = true;
    }
}
