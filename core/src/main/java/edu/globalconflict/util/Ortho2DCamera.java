package edu.globalconflict.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author mateusz
 * @since 29.08.14
 */
public class Ortho2DCamera extends OrthographicCamera {
    private Rectangle areaLimit = new Rectangle(-Float.MAX_VALUE, Float.MAX_VALUE, -Float.MAX_VALUE, Float.MAX_VALUE);
    private float zoomMin = Float.MIN_VALUE;
    private float zoomMax = Float.MAX_VALUE;

    public Ortho2DCamera(float width, float height) {
        this.viewportWidth = width;
        this.viewportHeight = height;
    }

    @Override
    public void update() {
        float correctionX, correctionY;
        boolean limit = false;

        // limit camera movement area
        if ((correctionX = areaLimit.x - getLeft()) > 0) {
            limit = true;
        } else if ((correctionX = areaLimit.width - getRight()) < 0) {
            limit = true;
        } else {
            correctionX = 0;
        }

        if ((correctionY = areaLimit.y - getBottom()) > 0) {
            limit = true;
        } else if ((correctionY = areaLimit.height - getTop()) < 0) {
            limit = true;
        } else {
            correctionY = 0;
        }

        if (limit) {
            translate(correctionX, correctionY);
        }

        // limit zoom
        zoom = MathUtil.clamp(zoom, zoomMin, zoomMax);

        super.update();
    }

    public float getLeft() {
        return position.x + (zoom * -viewportWidth / 2);
    }

    public float getRight() {
        return position.x + (zoom * viewportWidth / 2);
    }

    public float getBottom() {
        return position.y + (zoom * -viewportHeight / 2);
    }

    public float getTop() {
        return position.y + (zoom * viewportHeight / 2);
    }

    public void setAreaLimit(float x, float y, float w, float h) {
        areaLimit.set(x, y, w, h);
    }

    public void setZoomLimit(float min, float max) {
        zoomMin = min;
        zoomMax = max;
    }
}
