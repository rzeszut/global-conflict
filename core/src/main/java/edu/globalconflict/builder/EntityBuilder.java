package edu.globalconflict.builder;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.globalconflict.Constants;
import edu.globalconflict.component.*;
import edu.globalconflict.component.territory.TerritoryBounds;
import edu.globalconflict.entity.Component;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;

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

    public EntityBuilder newEntity(Tag.Namespace namespace, String tag) {
        this.entity = manager.newTaggedEntity(namespace, tag);
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

    public EntityBuilder withBounds (float... bounds) {
        assert bounds.length >= 6;
        assert bounds.length % 2 == 0;

        final Size size = manager.getComponent(entity, Size.class);
        assert size != null;

        final int count = bounds.length / 2;

        for (int i = 0; i < count; ++i) {
            float y = bounds[2 * i + 1];
            bounds[2 * i + 1] = size.height - y;
        }

        manager.addComponent(entity, new TerritoryBounds(bounds));
        return this;
    }
}
