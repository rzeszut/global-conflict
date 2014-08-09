package globalconflict.util;

import com.badlogic.gdx.math.Rectangle;

/**
 * @author mateusz
 * @since 25.04.14
 */
public final class Rect extends Rectangle {
    public Rect() {
    }

    public Rect(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public float getTop() {
        return y + height;
    }

    public float getBottom() {
        return y;
    }

    public float getLeft() {
        return x;
    }

    public float getRight() {
        return x + width;
    }

    public float getCenterX() {
        return x + width / 2;
    }

    public float getCenterY() {
        return y + height / 2;
    }

    public boolean collides(Rect other) {
        final boolean notCollides = (y >= other.y + other.height)
                || (y + height <= other.y)
                || (x >= other.x + other.width)
                || (x + width <= other.x);

        return !notCollides;
    }
}
