package edu.globalconflict;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * @author mateusz
 * @since 11.08.14
 */
public final class GameAssets {
    public static TextureAtlas world;
    public static BitmapFont armyFont;

    public static void load() {
        if (world == null) {
            world = new TextureAtlas(Gdx.files.internal("image/gen/world.atlas"));
        }
        if (armyFont == null) {
            armyFont = new BitmapFont(Gdx.files.internal("font/army.fnt"));
        }
    }

    public static void dispose() {
        if (world != null) {
            world.dispose();
            world = null;
        }
        if (armyFont != null) {
            armyFont.dispose();
            armyFont = null;
        }
    }
}
