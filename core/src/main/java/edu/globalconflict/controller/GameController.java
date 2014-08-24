package edu.globalconflict.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import edu.globalconflict.Constants;
import edu.globalconflict.component.io.PlayerClick;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;

import java.util.UUID;

/**
 * @author mateusz
 * @since 12.08.14
 */
public final class GameController extends GestureDetector {
    private static final float ZOOM_STEP = 0.08f;

    private OrthographicCamera camera;

    public GameController(OrthographicCamera camera, EntityManager entityManager) {
        super(new GestureController(camera, entityManager));
        this.camera = camera;
    }

    @Override
    public boolean scrolled (int amount) {
        final float zoomDiff = 1 + amount * ZOOM_STEP;
        camera.zoom *= zoomDiff;
        return true;
    }

    static class GestureController implements GestureDetector.GestureListener {
        private final OrthographicCamera camera;
        private final EntityManager entityManager;

        private float lastInitialDistance = 0.f;
        private float lastZoom = 1.f;
        private Vector3 unprojectedClick = new Vector3();

        GestureController(OrthographicCamera camera, EntityManager entityManager) {
            this.camera = camera;
            this.entityManager = entityManager;
        }

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            final UUID gameEntity = entityManager.getEntityForTag(Tag.Namespace.GAME, Constants.GAME_ENTITY);
            final PlayerClick playerClick = entityManager.getComponent(gameEntity, PlayerClick.class);

            unprojectedClick.set(x, y, 0);
            camera.unproject(unprojectedClick);
            playerClick.set(unprojectedClick.x, unprojectedClick.y);

            return true;
        }

        @Override
        public boolean longPress(float x, float y) {
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            camera.translate(-deltaX / 1.5f * camera.zoom,
                    deltaY / 1.5f * camera.zoom, 0);
            return true;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            if (lastInitialDistance != initialDistance) {
                lastInitialDistance = initialDistance;
                lastZoom = camera.zoom;
            }
            camera.zoom = lastZoom * initialDistance / distance;
            return true;
        }

        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;
        }
    }
}
