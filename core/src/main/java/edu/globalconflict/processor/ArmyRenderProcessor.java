package edu.globalconflict.processor;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.globalconflict.GameAssets;
import edu.globalconflict.component.Player;
import edu.globalconflict.component.Position;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.component.territory.PolygonCentroid;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Processor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author mateusz
 * @since 22.08.14
 */
public final class ArmyRenderProcessor implements Processor {
    private OrthographicCamera camera;
    private Batch batch;
    private BitmapFont bitmapFont = GameAssets.armyFont;

    public ArmyRenderProcessor(OrthographicCamera camera) {
        this.camera = camera;
        this.batch = new SpriteBatch();
    }

    @Override
    public void process(EntityManager entityManager, float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // iterate over armies
        final Set<Map.Entry<UUID, Army>> armyEntities =
                entityManager.getEntitiesWithComponentsForType(Army.class);
        for (Map.Entry<UUID, Army> armyEntity : armyEntities) {
            final UUID entity = armyEntity.getKey();
            final Army army = armyEntity.getValue();
            final Player player = entityManager.getComponent(entity, Player.class);

            final Position position = entityManager.getComponent(entity, Position.class);
            final PolygonCentroid centroid = entityManager.getComponent(entity, PolygonCentroid.class);
            final float x = position.x + centroid.x;
            final float y = position.y + centroid.y;

            bitmapFont.setColor(player.color);
            bitmapFont.draw(batch, army.getText(), x, y);
        }

        batch.end();
    }
}
