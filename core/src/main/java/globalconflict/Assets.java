package globalconflict;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author mateusz
 * @since 10.08.14
 */
public final class Assets {
    public static Skin skin;

    public static void load() {
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    }

    public static void dispose() {
        skin.dispose();
    }
}
