package edu.globalconflict.processor;

import com.badlogic.gdx.math.Intersector;
import edu.globalconflict.Constants;
import edu.globalconflict.component.*;
import edu.globalconflict.component.game.PlayerClick;
import edu.globalconflict.component.territory.TerritoryBounds;
import edu.globalconflict.component.territory.TerritorySelected;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Processor;
import edu.globalconflict.entity.Tag;

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
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
        final PlayerClick playerClick = entityManager.getComponent(gameEntity, PlayerClick.class);

        // if player just clicked
        if (playerClick.isNew) {
            // select territories
            selectTerritories(entityManager, playerClick);

            // click processed
            playerClick.isNew = false;
        }
    }

    private void selectTerritories(EntityManager entityManager, PlayerClick click) {
        // iterate over selectable territories
        final Set<Map.Entry<UUID, TerritorySelected>> selectableEntities =
                entityManager.getEntitiesWithComponentsForType(TerritorySelected.class);
        for (Map.Entry<UUID, TerritorySelected> selectableEntity : selectableEntities) {
            final UUID territoryEntity = selectableEntity.getKey();
            final TerritorySelected territorySelected = selectableEntity.getValue();

            // get territory position, size and bounds
            final Position position = entityManager.getComponent(territoryEntity, Position.class);
            final Size size = entityManager.getComponent(territoryEntity, Size.class);
            final TerritoryBounds bounds = entityManager.getComponent(territoryEntity, TerritoryBounds.class);

            final float x = click.x - position.x;
            final float y = click.y - position.y;

            // select territory if clicked point is inside rectangle and inside polygon bounds
            final boolean selected = (x > 0 && y > 0) && (x < size.width && y < size.height)
                            && Intersector.isPointInPolygon(bounds.bounds, 0, bounds.bounds.length, x, y);
            if (selected) {
                territorySelected.select(true);
                break;
            }
        }
    }
}
