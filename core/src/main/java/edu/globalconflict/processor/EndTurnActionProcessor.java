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

        // TODO
        // 3. unfreeze player territories (armies, actually)

        // 4. calculate available troops for player
        //   a. territories
        //   b. completed continents

        // 5. update available troops label
        availableTroopsLabel.setColor(currentPlayer.currentPlayer.color);
        availableTroopsLabel.setText("Available troops: " + currentPlayer.currentPlayer.availableTroops);

        // 6. clear selected territories
    }
}
