package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.UUID;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class UUIDSerializer implements Json.Serializer<UUID> {
    @Override
    public void write(Json json, UUID object, Class knownType) {
        json.writeValue(object.toString());
    }

    @Override
    public UUID read(Json json, JsonValue jsonData, Class type) {
        return UUID.fromString(jsonData.asString());
    }
}
