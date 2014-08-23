package edu.globalconflict.entity;

import edu.globalconflict.Constants;

import java.util.UUID;

/**
 * @author mateusz
 * @since 23.08.14
 */
public abstract class EventProcessor<C extends EventComponent> implements Processor {
    private final Class<C> eventComponentClass;

    protected EventProcessor(Class<C> eventComponentClass) {
        this.eventComponentClass = eventComponentClass;
    }

    @Override
    public final void process(EntityManager entityManager, float delta) {
        final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
        final C event = entityManager.getComponent(gameEntity, eventComponentClass);

        if (event.isNew) {
            // process
            processEvent(entityManager, delta, gameEntity, event);

            // action processed
            event.isNew = false;
        }
    }

    protected abstract void processEvent(EntityManager entityManager, float delta, UUID gameEntity, C event);
}
