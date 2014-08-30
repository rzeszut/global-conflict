package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.component.territory.Army;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class ArmySerializer implements Json.Serializer<Army> {
    @Override
    public void write(Json json, Army object, Class knownType) {
        json.writeObjectStart();
        json.writeValue("troops", object.troops);
        json.writeValue("frozen", object.frozen);
        json.writeObjectEnd();
    }

    @Override
    public Army read(Json json, JsonValue jsonData, Class type) {
        final Army army = new Army();
        army.troops = jsonData.getInt("troops");
        army.frozen = jsonData.getBoolean("frozen");
        return army;
    }
}
