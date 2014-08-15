package edu.globalconflict.entity;

/**
 * @author mateusz
 * @since 15.08.14
 */
public interface Processor {
    void process(EntityManager entityManager, float delta);
}
