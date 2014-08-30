package edu.globalconflict.serializer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.component.Player;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class PlayerSerializer implements Json.Serializer<Player> {
    @Override
    public void write(Json json, Player object, Class knownType) {
        json.writeObjectStart();
        json.writeValue("name", object.name);
        json.writeValue("color", object.color);
        json.writeValue("availableTroops", object.availableTroops);
        json.writeObjectEnd();
    }

    @Override
    public Player read(Json json, JsonValue jsonData, Class type) {
        final String name = jsonData.getString("name");
        final Color color = json.readValue("color", Color.class, jsonData);
        final Player player = new Player(name, color);
        player.availableTroops = jsonData.getInt("availableTroops");
        return player;
    }
}
