package edu.globalconflict.processor;

import com.badlogic.gdx.graphics.Color;
import edu.globalconflict.Constants;
import edu.globalconflict.component.territory.TerritorySelected;
import edu.globalconflict.component.TintColor;
import edu.globalconflict.component.game.SelectedTerritoriesStack;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Processor;
import edu.globalconflict.entity.Tag;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author mateusz
 * @since 16.08.14
 */
public final class TerritorySelectedProcessor implements Processor {
    private static final Color MODIFIER = new Color(0.5f, 0.5f, 0.5f, 0);

    @Override
    public void process(EntityManager entityManager, float delta) {
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
        final SelectedTerritoriesStack territoriesStack =
                entityManager.getComponent(gameEntity, SelectedTerritoriesStack.class);

        // iterate over selectable territories
        final Set<Map.Entry<UUID, TerritorySelected>> selectedTerritories =
                entityManager.getEntitiesWithComponentsForType(TerritorySelected.class);
        for (Map.Entry<UUID, TerritorySelected> selectedEntity : selectedTerritories) {
            final UUID entity = selectedEntity.getKey();
            final TerritorySelected selected = selectedEntity.getValue();

            // if territory was selected recently
            if (selected.isNew) {
                // get its tint color
                final TintColor color = entityManager.getComponent(entity, TintColor.class);

                // modify it according to selection and push changes
                if (selected.selected) {
                    color.color.sub(MODIFIER);
                    territoriesStack.territories.push(entity);
                } else {
                    color.color.add(MODIFIER);
                    territoriesStack.territories.remove(entity);
                }

                // selection processed
                selected.isNew = false;
            }
        }
    }
}
