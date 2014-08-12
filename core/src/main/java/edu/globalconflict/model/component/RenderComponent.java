package edu.globalconflict.model.component;

import com.badlogic.gdx.graphics.g2d.Batch;
import edu.globalconflict.model.GameEntity;

/**
 * @author mateusz
 * @since 12.08.14
 */
public interface RenderComponent {
    void render(GameEntity entity, Batch batch);

    static final RenderComponent NULL_OBJECT = new RenderComponent() {
        @Override
        public void render(GameEntity entity, Batch batch) {
        }
    };
}
