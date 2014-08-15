package edu.globalconflict.component;

import com.badlogic.gdx.graphics.Color;
import edu.globalconflict.entity.Component;

/**
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
