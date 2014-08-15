package edu.globalconflict.processor;

import edu.globalconflict.component.PlayerClick;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Processor;

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
        final Set<Map.Entry<UUID, PlayerClick>> playerClicks
                = entityManager.getEntitiesWithComponentsForType(PlayerClick.class);
        for (Map.Entry<UUID, PlayerClick> entry : playerClicks) {
            final PlayerClick playerClick = entry.getValue();

            if (playerClick.isNew) {
                System.out.format("Clicked at (%f, %f)%n", playerClick.x, playerClick.y);
                playerClick.reset();
            }
        }
    }
}
