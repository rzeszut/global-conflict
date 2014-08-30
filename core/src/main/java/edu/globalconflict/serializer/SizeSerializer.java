package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.component.Size;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class SizeSerializer implements Json.Serializer<Size> {
    @Override
    public void write(Json json, Size object, Class knownType) {
        json.writeObjectStart();
        json.writeValue("width", object.width);
        json.writeValue("height", object.height);
        json.writeObjectEnd();
    }

    @Override
    public Size read(Json json, JsonValue jsonData, Class type) {
        final float width = jsonData.getFloat("width");
        final float height = jsonData.getFloat("height");
        return new Size(width, height);
    }
}
