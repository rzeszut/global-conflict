package edu.globalconflict.processor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.globalconflict.component.Position;
import edu.globalconflict.component.Texture;
import edu.globalconflict.component.TintColor;
import edu.globalconflict.entity.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author mateusz
 * @since 15.08.14
 */
public final class RenderProcessor implements Processor {
    private OrthographicCamera camera;
    private Batch batch;

    public RenderProcessor(OrthographicCamera camera) {
        this.camera = camera;
        this.batch = new SpriteBatch();
    }

    @Override
    public void process(EntityManager entityManager, float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // draw textures
        final Set<Map.Entry<UUID, Texture>> entitiesWithComponents =
                entityManager.getEntitiesWithComponentsForType(Texture.class);
        for (Map.Entry<UUID, Texture> entry : entitiesWithComponents) {
            final UUID entity = entry.getKey();
            final Texture texture = entry.getValue();

            final TintColor tintColor = entityManager.getComponent(entity, TintColor.class);
            final Position position = entityManager.getComponent(entity, Position.class);

            batch.setColor(tintColor == null ? Color.WHITE : tintColor.color);
            batch.draw(texture.region, position.x, position.y);
        }

        batch.end();
    }
}
