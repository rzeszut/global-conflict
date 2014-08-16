package edu.globalconflict.processor;

import com.badlogic.gdx.graphics.Color;
import edu.globalconflict.component.TerritorySelected;
import edu.globalconflict.component.TintColor;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Processor;

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

                // modify it according to selection
                if (selected.selected) {
                    color.color.sub(MODIFIER);
                } else {
                    color.color.add(MODIFIER);
                }

                // selection processed
                selected.isNew = false;
            }
        }
    }
}
