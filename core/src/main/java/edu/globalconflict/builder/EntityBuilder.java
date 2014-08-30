package edu.globalconflict.builder;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import edu.globalconflict.Constants;
import edu.globalconflict.GameAssets;
import edu.globalconflict.component.Continent;
import edu.globalconflict.component.Position;
import edu.globalconflict.component.Size;
import edu.globalconflict.component.Texture;
import edu.globalconflict.component.territory.PolygonCentroid;
import edu.globalconflict.component.territory.Territory;
import edu.globalconflict.component.territory.TerritoryBounds;
import edu.globalconflict.entity.Component;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;
import edu.globalconflict.util.MathUtil;

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

    /**
     * This method relies on the fact that {@link GameAssets} are loaded.
     * NEVER call this before {@link GameAssets#load()}.
     *
     * @param regionName Region name
     * @return Builder
     */
    public EntityBuilder withTexture(String regionName) {
        final TextureAtlas.AtlasRegion region = GameAssets.world.findRegion(regionName);
        manager.addComponent(entity, new Texture(region));
        manager.addComponent(entity, new Size(region.getRegionWidth(), region.getRegionHeight()));
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

        final Vector2 centroid = MathUtil.calculatePolygonCentroid(bounds);
        manager.addComponent(entity, new PolygonCentroid(centroid.x, centroid.y));

        return this;
    }

    public EntityBuilder withTerritory(String name, String... neighbors) {
        manager.addComponent(entity, new Territory(name, neighbors));
        manager.tagEntity(entity, Tag.Namespace.TERRITORY, name);
        return this;
    }

    public EntityBuilder withContinent(int points, String... territories) {
        manager.addComponent(entity, new Continent(points, territories));
        return this;
    }
}
