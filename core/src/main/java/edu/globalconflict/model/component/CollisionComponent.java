package edu.globalconflict.model.component;

import edu.globalconflict.model.GameEntity;

/**
 * @author mateusz
 * @since 12.08.14
 */
public interface CollisionComponent {
    boolean hit(GameEntity entity, float x, float y);

    static final CollisionComponent NULL_OBJECT = new CollisionComponent() {
        @Override
        public boolean hit(GameEntity entity, float x, float y) {
            return false;
        }
    };
}
