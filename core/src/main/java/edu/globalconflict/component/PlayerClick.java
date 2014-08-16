package edu.globalconflict.component;

import edu.globalconflict.entity.Component;

/**
 * Component representing player click event.
 *
 * @author mateusz
 * @since 15.08.14
 */
public final class PlayerClick implements Component {
    /**
     * Click coordinates.
     */
    public float x = 0, y = 0;
    /**
     * Is this a new event.
     */
    public boolean isNew = false;

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
        this.isNew = true;
    }
}
