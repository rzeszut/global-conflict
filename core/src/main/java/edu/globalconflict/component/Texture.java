package edu.globalconflict.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.globalconflict.entity.Component;

/**
 * Component containing texture data for drawable entity.
 *
 * @author mateusz
 * @since 15.08.14
 */
public final class Texture implements Component {
    public TextureRegion region;

    public Texture(TextureRegion region) {
        this.region = region;
    }
}
