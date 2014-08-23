package edu.globalconflict.processor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.game.EndTurnAction;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;

import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public class EndTurnActionProcessor extends EventProcessor<EndTurnAction> {
    private final Label currentPlayerLabel;

    public EndTurnActionProcessor(Label currentPlayerLabel) {
        super(EndTurnAction.class);
        this.currentPlayerLabel = currentPlayerLabel;
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, EndTurnAction event) {
        // 1. change to next player
        final CurrentPlayer currentPlayer = entityManager.getComponent(gameEntity, CurrentPlayer.class);
        currentPlayer.nextPlayer();

        // 2. update current player label
        currentPlayerLabel.setColor(currentPlayer.currentPlayer.color);
        currentPlayerLabel.setText(currentPlayer.currentPlayer.name);

        // 3. unfreeze player territories (armies, actually)
        // 4. calculate available troops for player -- game logic
        // 5. update available troops label
    }
}
