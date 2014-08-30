package edu.globalconflict.screen.loading.action;

import edu.globalconflict.Constants;
import edu.globalconflict.component.game.AttackAction;
import edu.globalconflict.component.game.EndTurnAction;
import edu.globalconflict.component.game.TransferAction;
import edu.globalconflict.component.io.*;
import edu.globalconflict.component.territory.Territory;
import edu.globalconflict.component.territory.TerritorySelected;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;
import edu.globalconflict.serializer.GameSerializer;

import java.util.UUID;

/**
 * @author mateusz
 * @since 29.08.14
 */
public final class LoadGameAction implements GameCreateAction {
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void run() {
        entityManager = GameSerializer.load(Constants.SAVE_FILE);
        if (entityManager == null) {
            return;
        }

        // add missing components for territories
        for (UUID territory : entityManager.getEntitiesForType(Territory.class)) {
            entityManager.addComponent(territory, new TerritorySelected());
        }

        // add missing game components
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);

        entityManager.addComponent(gameEntity, new AttackAction());
        entityManager.addComponent(gameEntity, new EndTurnAction());
        entityManager.addComponent(gameEntity, new TransferAction());

        entityManager.addComponent(gameEntity, new AttackButtonClick());
        entityManager.addComponent(gameEntity, new GameError());
        entityManager.addComponent(gameEntity, new PlayerClick());
        entityManager.addComponent(gameEntity, new SelectedTerritoriesStack());
        entityManager.addComponent(gameEntity, new TransferButtonClick());
    }
}
