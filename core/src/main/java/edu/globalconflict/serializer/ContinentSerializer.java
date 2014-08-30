package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.component.Continent;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class ContinentSerializer implements Json.Serializer<Continent> {
    @Override
    public void write(Json json, Continent object, Class knownType) {
        json.writeObjectStart();

        json.writeValue("bonus", object.bonus);

        json.writeArrayStart("territories");
        for (String territory : object.territories) {
            json.writeValue(territory);
        }
        json.writeArrayEnd();

        json.writeObjectEnd();
    }

    @Override
    public Continent read(Json json, JsonValue jsonData, Class type) {
        final int bonus = jsonData.getInt("bonus");
        final String[] territories = jsonData.get("territories").asStringArray();
        return new Continent(bonus, territories);
    }
}
