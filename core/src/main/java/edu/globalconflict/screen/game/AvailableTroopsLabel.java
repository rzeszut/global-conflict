package edu.globalconflict.screen.game;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.globalconflict.MainAssets;
import edu.globalconflict.component.game.CurrentPlayer;

/**
 * @author mateusz
 * @since 28.08.14
 */
public final class AvailableTroopsLabel extends Label {
    public AvailableTroopsLabel() {
        super("", MainAssets.skin);
    }

    public void update(CurrentPlayer currentPlayer) {
        setColor(currentPlayer.currentPlayer.color);
        setText("Available troops: " + currentPlayer.currentPlayer.availableTroops);
    }
}
