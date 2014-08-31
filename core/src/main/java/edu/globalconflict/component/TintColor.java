package edu.globalconflict.component;

import com.badlogic.gdx.graphics.Color;
import edu.globalconflict.entity.Component;

/**
 * Component representing entity's color.
 * Used in texture drawing for changing the base color of a texture.
 *
 * @author mateusz
 * @since 15.08.14
 */
public final class TintColor implements Component {
    public Color color = Color.WHITE.cpy();

    public TintColor() {
    }

    public TintColor(Color color) {
        this.color.set(color);
    }
}
