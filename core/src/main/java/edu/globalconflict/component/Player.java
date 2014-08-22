package edu.globalconflict.component;

import com.badlogic.gdx.graphics.Color;
import edu.globalconflict.entity.Component;

/**
 * Class representing single player data.
 *
 * @author mateusz
 * @since 22.08.14
 */
public final class Player implements Component {
    /**
     * Player name.
     */
    public final String name;
    /**
     * Player color.
     */
    public final Color color;
    /**
     * Troops available for transfer
     */
    public int availableTroops = 0;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Player player = (Player) o;
        return availableTroops == player.availableTroops &&
                color.equals(player.color) &&
                name.equals(player.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + availableTroops;
        return result;
    }
}
