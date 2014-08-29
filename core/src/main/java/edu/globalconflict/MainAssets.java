package edu.globalconflict;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author mateusz
 * @since 10.08.14
 */
public final class MainAssets {
    public static Skin skin;
    public static Texture logo;

    public static void load() {
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        logo = new Texture(Gdx.files.internal("image/logo.png"));
        logo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public static void dispose() {
        skin.dispose();
        logo.dispose();
    }
}
