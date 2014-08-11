package edu.globalconflict.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

/**
 * @author mateusz
 * @since 10.08.14
 */
public class Territory extends Actor {
    Array<Vector2> polygonBounds;
    TextureRegion texture;
    String name;

    public Territory() {
        setTouchable(Touchable.enabled);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        // TODO
    }

    @Override
    public Actor hit (float x, float y, boolean touchable) {
        // TODO: check collision with polygon bounding if super.hit()
        return super.hit(x, y, touchable);
    }
}
