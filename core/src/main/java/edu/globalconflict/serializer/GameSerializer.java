package edu.globalconflict.serializer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import edu.globalconflict.component.*;
import edu.globalconflict.component.game.CurrentPlayer;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.component.territory.PolygonCentroid;
import edu.globalconflict.component.territory.Territory;
import edu.globalconflict.component.territory.TerritoryBounds;
import edu.globalconflict.entity.EntityManager;

import java.util.UUID;

/**
 * @author mateusz
 * @since 30.08.14
 */
public final class GameSerializer {
    private static final Json json;
    static {
        json = new Json(JsonWriter.OutputType.json);

        json.setSerializer(EntityManager.class, new EntityManagerSerializer());
        json.setSerializer(UUID.class, new UUIDSerializer());

        json.setSerializer(Continent.class, new ContinentSerializer());
        json.setSerializer(Player.class, new PlayerSerializer());
        json.setSerializer(Position.class, new PositionSerializer());
        json.setSerializer(Size.class, new SizeSerializer());
        json.setSerializer(Texture.class, new TextureSerializer());

        json.setSerializer(Army.class, new ArmySerializer());
        json.setSerializer(PolygonCentroid.class, new PolygonCentroidSerializer());
        json.setSerializer(Territory.class, new TerritorySerializer());
        json.setSerializer(TerritoryBounds.class, new TerritoryBoundsSerializer());

        json.setSerializer(CurrentPlayer.class, new CurrentPlayerSerializer());
    }

    private GameSerializer() {
    }

    public static void save(EntityManager entityManager, String filename) {
        final FileHandle file = Gdx.files.local(filename);
        json.toJson(entityManager, file);
    }

    public static EntityManager load(String filename) {
        final FileHandle file = Gdx.files.local(filename);
        return json.fromJson(EntityManager.class, file);
    }
}
