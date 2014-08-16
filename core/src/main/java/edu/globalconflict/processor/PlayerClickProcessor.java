package edu.globalconflict.processor;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import edu.globalconflict.component.*;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Processor;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author mateusz
 * @since 15.08.14
 */
public final class PlayerClickProcessor implements Processor {

    @Override
    public void process(EntityManager entityManager, float delta) {
        // TODO: this processor should check only for current player's events

        // iterate over player's click events
        final Collection<PlayerClick> playerClicks
                = entityManager.getComponentsForType(PlayerClick.class);
        for (PlayerClick playerClick : playerClicks) {
            // if player just clicked
            if (playerClick.isNew) {
                // select territories
                selectTerritories(entityManager, playerClick);

                // click processed
                playerClick.isNew = false;
            }
        }
    }

    private void selectTerritories(EntityManager entityManager, PlayerClick click) {
        // iterate over selectable territories
        final Set<Map.Entry<UUID, TerritorySelected>> selectables =
                entityManager.getEntitiesWithComponentsForType(TerritorySelected.class);
        for (Map.Entry<UUID, TerritorySelected> selectable : selectables) {
            final UUID territoryEntity = selectable.getKey();
            final TerritorySelected territorySelected = selectable.getValue();

            // get territory position, size and bounds
            final Position position = entityManager.getComponent(territoryEntity, Position.class);
            final Size size = entityManager.getComponent(territoryEntity, Size.class);
            final Bounds bounds = entityManager.getComponent(territoryEntity, Bounds.class);

            // set temp variable
            final float x = click.x - position.x;
            final float y = click.y - position.y;

            // select territory if clicked point is inside rectangle and inside polygon bounds
            territorySelected.select(
                    (x > 0 && y > 0) && (x < size.width && y < size.height)
                            && Intersector.isPointInPolygon(bounds.bounds, 0, bounds.bounds.length, x, y));
        }
    }
}
