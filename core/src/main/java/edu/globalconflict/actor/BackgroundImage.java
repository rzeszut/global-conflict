package edu.globalconflict.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * @author mateusz
 * @since 11.08.14
 */
public class BackgroundImage extends Actor {
    TextureRegion texture;

    public BackgroundImage() {
        setTouchable(Touchable.disabled);
    }
}
