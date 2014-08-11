package edu.globalconflict;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author mateusz
 * @since 11.08.14
 */
public final class GameAssets {
    private static TextureAtlas america;
    public static TextureRegion alaska;
    public static TextureRegion northwestTerritory;
    public static TextureRegion alberta;
    public static TextureRegion ontario;
    public static TextureRegion quebec;
    public static TextureRegion easternUS;
    public static TextureRegion westernUS;
    public static TextureRegion centralAmerica;
    public static TextureRegion greenland;

    public static void load() {
        if (america == null) {
            america = new TextureAtlas(Gdx.files.internal("image/gen/america.atlas"));

            alaska = america.findRegion("alaska");
            northwestTerritory = america.findRegion("northwest-territory");
            alberta = america.findRegion("alberta");
            ontario = america.findRegion("ontario");
            quebec = america.findRegion("quebec");
            easternUS = america.findRegion("eastern-us");
            westernUS = america.findRegion("western-us");
            centralAmerica = america.findRegion("central-america");
            greenland = america.findRegion("greenland");
        }
    }

    public static void dispose() {
        if (america != null) {
            america.dispose();
            america = null;
        }
    }
}
