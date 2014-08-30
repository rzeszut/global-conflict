package edu.globalconflict.component;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import edu.globalconflict.entity.Component;

/**
 * Component containing texture data for drawable entity.
 *
 * @author mateusz
 * @since 15.08.14
 */
public final class Texture implements Component {
    public TextureAtlas.AtlasRegion region;

    public Texture(TextureAtlas.AtlasRegion region) {
        this.region = region;
    }
}
