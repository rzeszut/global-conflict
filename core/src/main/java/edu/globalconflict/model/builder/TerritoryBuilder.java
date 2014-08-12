package edu.globalconflict.model.builder;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import edu.globalconflict.model.component.TerritoryCollisionComponent;

/**
 * @author mateusz
 * @since 12.08.14
 */
public final class TerritoryBuilder extends AbstractEntityBuilder<TerritoryBuilder> {
    public TerritoryBuilder withBounds(float[] indices) {
        assert indices.length >= 6;
        assert indices.length % 2 == 0;

        final int count = indices.length / 2;
        final Array<Vector2> bounds = new Array<>(count);

        for (int i = 0; i < count; ++i) {
            float x = indices[2 * i], y = indices[2 * i + 1];
            bounds.insert(i, new Vector2(x, item.area.width - y));
        }

        item.collisionComponent = new TerritoryCollisionComponent(bounds);

        return this;
    }

    @Override
    protected TerritoryBuilder self() {
        return this;
    }
}
