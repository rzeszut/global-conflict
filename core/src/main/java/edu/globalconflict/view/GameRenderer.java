package edu.globalconflict.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.globalconflict.model.World;
import edu.globalconflict.model.GameEntity;

/**
 * @author mateusz
 * @since 12.08.14
 */
public final class GameRenderer {
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private World world;

    public GameRenderer(OrthographicCamera camera, World world) {
        this.camera = camera;
        this.batch = new SpriteBatch();

        this.world = world;
    }

    public void render() {
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (GameEntity entity : world.entities) {
            entity.renderComponent.render(entity, batch);
        }

        batch.end();
    }
}
