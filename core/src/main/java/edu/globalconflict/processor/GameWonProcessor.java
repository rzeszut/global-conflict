package edu.globalconflict.processor;

import edu.globalconflict.Constants;
import edu.globalconflict.component.game.GameWon;
import edu.globalconflict.component.io.GameError;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.UUID;

/**
 * Created by jtekiela on 31.08.2014.
 */
public class GameWonProcessor extends EventProcessor<GameWon> {
    public GameWonProcessor() {
        super(GameWon.class);
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, GameWon event) {

        // show error message
        entityManager.getComponent(gameEntity, GameError.class).set(Constants.GAME_WON);

    }
}
