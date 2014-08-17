package edu.globalconflict.processor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.globalconflict.component.territory.TerritoryBounds;
import edu.globalconflict.component.Position;
import edu.globalconflict.component.Size;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Processor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author mateusz
 * @since 16.08.14
 */
public final class DebugRenderProcessor implements Processor {
    private OrthographicCamera camera;
    private ShapeRenderer debugRenderer;

    public DebugRenderProcessor(OrthographicCamera camera) {
        this.camera = camera;
        this.debugRenderer = new ShapeRenderer();
        this.debugRenderer.setAutoShapeType(true);
    }

    @Override
    public void process(EntityManager entityManager, float delta) {
        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.begin();

        // iterate over entities with bounds
        final Set<Map.Entry<UUID, TerritoryBounds>> boundedEntities =
                entityManager.getEntitiesWithComponentsForType(TerritoryBounds.class);
        for (Map.Entry<UUID, TerritoryBounds> boundedEntity : boundedEntities) {
            final UUID entity = boundedEntity.getKey();

            // get bounds, position and size
            final TerritoryBounds bounds = boundedEntity.getValue();
            final Position position = entityManager.getComponent(entity, Position.class);
            final Size size = entityManager.getComponent(entity, Size.class);

            // translate to entity's local coordinates
            debugRenderer.getTransformMatrix().idt();
            debugRenderer.translate(position.x, position.y, 0);

            // draw rectangle area
            debugRenderer.setColor(Color.GREEN);
            debugRenderer.rect(0, 0, size.width, size.height);

            // draw polygon bounds
            debugRenderer.setColor(Color.RED);
            debugRenderer.polygon(bounds.bounds);
        }

        debugRenderer.end();
    }
}
