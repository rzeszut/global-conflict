package edu.globalconflict;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author mateusz
 * @since 11.08.14
 */
public final class GameAssets {
    private static TextureAtlas northAmerica;
    public static TextureRegion alaska;
    public static TextureRegion northwestTerritory;
    public static TextureRegion alberta;
    public static TextureRegion ontario;
    public static TextureRegion quebec;
    public static TextureRegion easternUS;
    public static TextureRegion westernUS;
    public static TextureRegion centralAmerica;
    public static TextureRegion greenland;

    private static TextureAtlas southAmerica;
    public static TextureRegion venezuela;
    public static TextureRegion brazil;
    public static TextureRegion peru;
    public static TextureRegion argentina;

    public static void load() {
        if (northAmerica == null) {
            northAmerica = new TextureAtlas(Gdx.files.internal("image/gen/north-america.atlas"));

            alaska = northAmerica.findRegion("alaska");
            northwestTerritory = northAmerica.findRegion("northwest-territory");
            alberta = northAmerica.findRegion("alberta");
            ontario = northAmerica.findRegion("ontario");
            quebec = northAmerica.findRegion("quebec");
            easternUS = northAmerica.findRegion("eastern-us");
            westernUS = northAmerica.findRegion("western-us");
            centralAmerica = northAmerica.findRegion("central-america");
            greenland = northAmerica.findRegion("greenland");
        }

        if (southAmerica == null) {
            southAmerica = new TextureAtlas(Gdx.files.internal("image/gen/south-america.atlas"));

            venezuela = southAmerica.findRegion("venezuela");
            brazil = southAmerica.findRegion("brazil");
            peru = southAmerica.findRegion("peru");
            argentina = southAmerica.findRegion("argentina");
        }
    }

    public static void dispose() {
        if (northAmerica != null) {
            northAmerica.dispose();
            northAmerica = null;
        }

        if (southAmerica != null) {
            southAmerica.dispose();
            southAmerica = null;
        }
    }
}
