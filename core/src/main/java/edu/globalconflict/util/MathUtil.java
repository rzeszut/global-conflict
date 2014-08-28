package edu.globalconflict.util;

import com.badlogic.gdx.math.Vector2;

/**
 * @author mateusz
 * @since 28.08.14
 */
public final class MathUtil {
    private MathUtil() {
    }

    /**
     * Calculates the centroid of a polygon.
     * {@see http://stackoverflow.com/questions/2792443/finding-the-centroid-of-a-polygon }
     * {@see http://en.wikipedia.org/wiki/Centroid#Centroid_of_polygon }
     *
     * @param polygon Array of floats: [x, y, x, y, ...]
     * @return Centroid
     */
    public static Vector2 calculatePolygonCentroid(float[] polygon) {
        assert polygon.length >= 6;
        assert polygon.length % 2 == 0;

        final Vector2 centroid = new Vector2(0, 0);
        float signedArea = 0, a;
        float x0, y0;
        float x1, y1;

        final int verticesCount = polygon.length / 2;

        // for all vertices except last one
        int i = 0;
        for (; i < verticesCount - 1; ++i) {
            x0 = polygon[2 * i];
            y0 = polygon[2 * i + 1];
            x1 = polygon[2 * i + 2];
            y1 = polygon[2 * i + 3];

            a = x0 * y1 - x1 * y0;
            signedArea += a;

            centroid.x += (x0 + x1) * a;
            centroid.y += (y0 + y1) * a;
        }

        // do the last vertex
        x0 = polygon[2 * i];
        y0 = polygon[2 * i + 1];
        x1 = polygon[0];
        y1 = polygon[1];

        a = x0 * y1 - x1 * y0;
        signedArea += a;

        centroid.x += (x0 + x1) * a;
        centroid.y += (y0 + y1) * a;

        // final calculations
        signedArea *= 3.0;
        centroid.x /= signedArea;
        centroid.y /= signedArea;

        return centroid;
    }
}
