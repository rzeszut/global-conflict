package edu.globalconflict.component.io;

import edu.globalconflict.entity.EventComponent;

/**
 * Component representing player click event.
 *
 * @author mateusz
 * @since 15.08.14
 */
public final class PlayerClick extends EventComponent {
    /**
     * Click coordinates.
     */
    public float x = 0, y = 0;

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
        this.isNew = true;
    }
}
