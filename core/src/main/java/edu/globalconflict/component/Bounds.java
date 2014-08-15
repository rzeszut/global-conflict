package edu.globalconflict.component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import edu.globalconflict.entity.Component;

/**
 * @author mateusz
 * @since 15.08.14
 */
public final class Bounds implements Component {
    public Array<Vector2> bounds;

    public Bounds(Array<Vector2> bounds) {
        this.bounds = bounds;
    }
}
