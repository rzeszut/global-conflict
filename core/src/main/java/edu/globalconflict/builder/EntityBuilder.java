package edu.globalconflict.builder;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import edu.globalconflict.Constants;
import edu.globalconflict.component.*;
import edu.globalconflict.entity.Component;
import edu.globalconflict.entity.EntityManager;

import java.util.UUID;

/**
 * @author mateusz
 * @since 15.08.14
 */
public final class EntityBuilder {
    private EntityManager manager;
    private UUID entity;

    public EntityBuilder(EntityManager manager) {
        this.manager = manager;
    }

    public EntityBuilder newEntity() {
        this.entity = manager.newEntity();
        return this;
    }

    public EntityBuilder newEntity(String... tags) {
        this.entity = manager.newTaggedEntity(tags);
        return this;
    }

    public <T extends Component> EntityBuilder withComponent(T component) {
        manager.addComponent(entity, component);
        return this;
    }

    public EntityBuilder withTexture(TextureRegion region) {
        manager.addComponent(entity, new Texture(region));
        manager.addComponent(entity, new Size(region.getRegionWidth(), region.getRegionHeight()));
        return this;
    }

    public EntityBuilder withTintColor(Color color) {
        manager.addComponent(entity, new TintColor(color));
        return this;
    }

    public EntityBuilder withPosition(float x, float y) {
        manager.addComponent(entity, new Position(x, Constants.WORLD_HEIGHT - y));
        return this;
    }

    public EntityBuilder withBounds (float[] indices) {
        assert indices.length >= 6;
        assert indices.length % 2 == 0;

        final Size size = manager.getComponent(entity, Size.class);
        assert size != null;

        final int count = indices.length / 2;
        final Array<Vector2> bounds = new Array<>(count);

        for (int i = 0; i < count; ++i) {
            float x = indices[2 * i], y = indices[2 * i + 1];
            bounds.insert(i, new Vector2(x, size.width - y));
        }

        manager.addComponent(entity, new Bounds(bounds));
        return this;
    }
}
