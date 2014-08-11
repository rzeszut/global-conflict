package edu.globalconflict.listener;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * @author mateusz
 * @since 10.08.14
 */
public class CameraGestureListener extends ActorGestureListener {
    private Camera camera;

    public CameraGestureListener(Camera camera) {
        this.camera = camera;
    }

    @Override
	public void pan (InputEvent event, float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX / 1.5f, -deltaY / 1.5f, 0);
    }
}
