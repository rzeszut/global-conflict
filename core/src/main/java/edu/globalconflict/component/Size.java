package edu.globalconflict.component;

import edu.globalconflict.entity.Component;

/**
 * Component representing entity size.
 *
 * @author mateusz
 * @since 15.08.14
 */
public final class Size implements Component {
    public float width, height;

    public Size(float width, float height) {
        this.width = width;
        this.height = height;
    }
}
