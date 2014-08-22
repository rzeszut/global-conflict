package edu.globalconflict.component.game;

import edu.globalconflict.component.Player;
import edu.globalconflict.entity.Component;
import edu.globalconflict.util.CycleIterator;

import java.util.Iterator;

/**
 * Component representing current player.
 *
 * @author mateusz
 * @since 22.08.14
 */
public final class CurrentPlayer implements Component {
    private Iterator<Player> playerIterator;
    /**
     * Current player.
     * Note that at the beginning this value is null: mock PlayerAction END TURN should be fired at the very beginning
     * of the game.
     */
    public Player currentPlayer;

    public CurrentPlayer(Iterable<Player> players) {
        this.playerIterator = new CycleIterator<>(players);
    }

    /**
     * Changes player to the next one.
     */
    public void nextPlayer() {
        if (playerIterator.hasNext()) {
            currentPlayer = playerIterator.next();
        }
    }
}
