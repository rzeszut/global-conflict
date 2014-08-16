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

    private static TextureAtlas africa;
    public static TextureRegion madagascar;
    public static TextureRegion southAfrica;
    public static TextureRegion congo;
    public static TextureRegion eastAfrica;
    public static TextureRegion westAfrica;
    public static TextureRegion egypt;

    private static TextureAtlas europe;
    public static TextureRegion iceland;
    public static TextureRegion greatBritain;
    public static TextureRegion westernEurope;
    public static TextureRegion southernEurope;
    public static TextureRegion northernEurope;
    public static TextureRegion scandinavia;
    public static TextureRegion ukraine;

    private static TextureAtlas australia;
    public static TextureRegion westernAustralia;
    public static TextureRegion easternAustralia;
    public static TextureRegion newGuinea;
    public static TextureRegion indonesia;

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

        if (africa == null) {
            africa = new TextureAtlas(Gdx.files.internal("image/gen/africa.atlas"));

            madagascar = africa.findRegion("madagascar");
            southAfrica = africa.findRegion("south-africa");
            congo = africa.findRegion("congo");
            eastAfrica = africa.findRegion("east-africa");
            westAfrica = africa.findRegion("west-africa");
            egypt = africa.findRegion("egypt");
        }

        if (europe == null) {
            europe = new TextureAtlas(Gdx.files.internal("image/gen/europe.atlas"));

            iceland = europe.findRegion("iceland");
            greatBritain = europe.findRegion("great-britain");
            westernEurope = europe.findRegion("western-europe");
            southernEurope = europe.findRegion("southern-europe");
            northernEurope = europe.findRegion("northern-europe");
            scandinavia = europe.findRegion("scandinavia");
            ukraine = europe.findRegion("ukraine");
        }

        if (australia == null) {
            australia = new TextureAtlas(Gdx.files.internal("image/gen/australia.atlas"));

            westernAustralia = australia.findRegion("western-australia");
            easternAustralia = australia.findRegion("eastern-australia");
            newGuinea = australia.findRegion("new-guinea");
            indonesia = australia.findRegion("indonesia");
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

        if (africa != null) {
            africa.dispose();
            africa = null;
        }

        if (europe != null) {
            europe.dispose();
            europe = null;
        }

        if (australia != null) {
            australia.dispose();
            australia = null;
        }
    }
}
