package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.component.Player;
import edu.globalconflict.component.game.CurrentPlayer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class CurrentPlayerSerializer implements Json.Serializer<CurrentPlayer> {
    @Override
    public void write(Json json, CurrentPlayer object, Class knownType) {
        json.writeObjectStart();

        json.writeValue("nextCalls", object.playerIterator.getCallCount());

        json.writeArrayStart("players");
        for (Player player : object.players) {
            json.writeValue(player);
        }
        json.writeArrayEnd();

        json.writeObjectEnd();
    }

    @Override
    public CurrentPlayer read(Json json, JsonValue jsonData, Class type) {
        final int nextCalls = jsonData.getInt("nextCalls");

        final Collection<Player> players = new ArrayList<>();
        for (JsonValue entry : jsonData.get("players")) {
            players.add(json.readValue(Player.class, entry));
        }

        final CurrentPlayer currentPlayer = new CurrentPlayer(players);

        // call iterator#next() enough times
        final int times = nextCalls % players.size();
        for (int i = 0; i < times; ++i) {
            currentPlayer.nextPlayer();
        }

        return currentPlayer;
    }
}
