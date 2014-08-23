package edu.globalconflict.processor;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.globalconflict.MainAssets;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.component.Player;
import edu.globalconflict.component.Position;
import edu.globalconflict.component.Size;
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
    // TODO: create separate font for in-game use -- a bigger one, that's certain -- and put it into GameAssets
    private BitmapFont bitmapFont = MainAssets.skin.getFont("default-font");

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

            // TODO: replace with pre-calculated centroid
            // http://stackoverflow.com/questions/2792443/finding-the-centroid-of-a-polygon
            // http://en.wikipedia.org/wiki/Centroid#Centroid_of_polygon
            final Position position = entityManager.getComponent(entity, Position.class);
            final Size size = entityManager.getComponent(entity, Size.class);
            final float x = position.x + size.width / 2;
            final float y = position.y + size.height / 2;

            bitmapFont.setColor(player.color);
            bitmapFont.draw(batch, army.getText(), x, y);
        }

        batch.end();
    }
}
