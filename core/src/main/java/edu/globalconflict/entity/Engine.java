package edu.globalconflict.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mateusz
 * @since 15.08.14
 */
public final class Engine {
    private List<Processor> processors;
    private EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.processors = new ArrayList<>();
    }

    public void update(float delta) {
        for (Processor processor : processors) {
            processor.process(entityManager, delta);
        }
    }

    public void registerProcessor(Processor processor) {
        this.processors.add(processor);
    }
}
