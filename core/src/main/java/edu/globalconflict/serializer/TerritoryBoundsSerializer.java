package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.component.territory.TerritoryBounds;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class TerritoryBoundsSerializer implements Json.Serializer<TerritoryBounds> {
    @Override
    public void write(Json json, TerritoryBounds object, Class knownType) {
        json.writeObjectStart();
        json.writeArrayStart("bounds");
        for (float f : object.bounds) {
            json.writeValue(f);
        }
        json.writeArrayEnd();
        json.writeObjectEnd();
    }

    @Override
    public TerritoryBounds read(Json json, JsonValue jsonData, Class type) {
        final float[] bounds = jsonData.get("bounds").asFloatArray();
        return new TerritoryBounds(bounds);
    }
}
