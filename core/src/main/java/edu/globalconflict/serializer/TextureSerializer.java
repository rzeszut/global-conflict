package edu.globalconflict.serializer;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.GameAssets;
import edu.globalconflict.component.Texture;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class TextureSerializer implements Json.Serializer<Texture> {
    @Override
    public void write(Json json, Texture object, Class knownType) {
        json.writeObjectStart();
        json.writeValue("regionName", object.region.name);
        json.writeObjectEnd();
    }

    /**
     * This method relies on {@link edu.globalconflict.GameAssets#load()} being called before.
     */
    @Override
    public Texture read(Json json, JsonValue jsonData, Class type) {
        final String regionName = jsonData.getString("regionName");
        final TextureAtlas.AtlasRegion region = GameAssets.world.findRegion(regionName);
        return new Texture(region);
    }
}
