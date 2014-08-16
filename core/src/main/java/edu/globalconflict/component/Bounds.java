package edu.globalconflict.component;

import edu.globalconflict.entity.Component;

/**
 * Component containing polygon bounds of an entity.
 *
 * @author mateusz
 * @since 15.08.14
 */
public final class Bounds implements Component {
    /**
     * Polygon bounds of an entity.
     * An array of vertices in format [x1, y1, x2, y2, ...], where x* and y* are in entity's local coordinates,
     * not world coordinates.
     */
    public float[] bounds;

    public Bounds(float[] bounds) {
        this.bounds = bounds;
    }
}
