package edu.globalconflict.screen.loading.action;

import edu.globalconflict.entity.EntityManager;

/**
 * @author mateusz
 * @since 29.08.14
 */
public interface GameCreateAction extends Runnable {
    EntityManager getEntityManager();
}
