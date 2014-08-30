package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.component.Position;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class PositionSerializer implements Json.Serializer<Position> {
    @Override
    public void write(Json json, Position object, Class knownType) {
        json.writeObjectStart();
        json.writeValue("x", object.x);
        json.writeValue("y", object.y);
        json.writeObjectEnd();
    }

    @Override
    public Position read(Json json, JsonValue jsonData, Class type) {
        final float x = jsonData.getFloat("x");
        final float y = jsonData.getFloat("y");
        return new Position(x, y);
    }
}
