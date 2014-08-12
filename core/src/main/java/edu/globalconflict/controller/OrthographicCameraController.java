package edu.globalconflict.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * @author mateusz
 * @since 12.08.14
 */
public class OrthographicCameraController implements GestureDetector.GestureListener {
    private OrthographicCamera camera;

    private float lastInitialDistance = 0.f;
    private float lastZoom = 1.f;

    public OrthographicCameraController(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
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
