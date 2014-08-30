package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.component.territory.PolygonCentroid;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class PolygonCentroidSerializer implements Json.Serializer<PolygonCentroid> {
    @Override
    public void write(Json json, PolygonCentroid object, Class knownType) {
        json.writeObjectStart();
        json.writeValue("x", object.x);
        json.writeValue("y", object.y);
        json.writeObjectEnd();
    }

    @Override
    public PolygonCentroid read(Json json, JsonValue jsonData, Class type) {
        final float x = jsonData.getFloat("x");
        final float y = jsonData.getFloat("y");
        return new PolygonCentroid(x, y);
    }
}
