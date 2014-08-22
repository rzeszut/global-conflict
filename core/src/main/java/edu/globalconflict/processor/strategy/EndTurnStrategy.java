package edu.globalconflict.processor.strategy;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.globalconflict.Constants;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.game.PlayerAction;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;

import java.util.UUID;

/**
 * @author mateusz
 * @since 22.08.14
 */
public class EndTurnStrategy implements PlayerActionStrategy {
    private Label currentPlayerLabel;

    public EndTurnStrategy(Label currentPlayerLabel) {
        this.currentPlayerLabel = currentPlayerLabel;
    }

    @Override
    public void process(EntityManager entityManager, PlayerAction.Action actionEvent) {
        // 1. change to next player
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
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
