package edu.globalconflict.component;

import edu.globalconflict.entity.Component;

/**
 * Component representing entity position in the game world.
 *
 * @author mateusz
 * @since 15.08.14
 */
public final class Position implements Component {
    public float x, y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
