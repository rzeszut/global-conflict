package edu.globalconflict.component.territory;

import edu.globalconflict.entity.Component;

/**
 * @author mateusz
 * @since 28.08.14
 */
public final class PolygonCentroid implements Component {
    public final float x, y;

    public PolygonCentroid(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
