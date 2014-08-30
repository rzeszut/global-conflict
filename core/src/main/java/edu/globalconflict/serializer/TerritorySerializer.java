package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.component.territory.Territory;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class TerritorySerializer implements Json.Serializer<Territory> {
    @Override
    public void write(Json json, Territory object, Class knownType) {
        json.writeObjectStart();

        json.writeValue("name", object.name);

        json.writeArrayStart("neighbors");
        for (String neighbor : object.neighbors) {
            json.writeValue(neighbor);
        }
        json.writeArrayEnd();

        json.writeObjectEnd();
    }

    @Override
    public Territory read(Json json, JsonValue jsonData, Class type) {
        final String name = jsonData.getString("name");
        final String[] neighbors = jsonData.get("neighbors").asStringArray();
        return new Territory(name, neighbors);
    }
}
