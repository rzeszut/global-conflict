package edu.globalconflict;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author mateusz
 * @since 11.08.14
 */
public final class GameAssets {
    private static TextureAtlas world;

    public static TextureRegion alaska;
    public static TextureRegion northwestTerritory;
    public static TextureRegion alberta;
    public static TextureRegion ontario;
    public static TextureRegion quebec;
    public static TextureRegion easternUS;
    public static TextureRegion westernUS;
    public static TextureRegion centralAmerica;
    public static TextureRegion greenland;

    public static TextureRegion venezuela;
    public static TextureRegion brazil;
    public static TextureRegion peru;
    public static TextureRegion argentina;

    public static TextureRegion madagascar;
    public static TextureRegion southAfrica;
    public static TextureRegion congo;
    public static TextureRegion eastAfrica;
    public static TextureRegion westAfrica;
    public static TextureRegion egypt;

    public static TextureRegion iceland;
    public static TextureRegion greatBritain;
    public static TextureRegion westernEurope;
    public static TextureRegion southernEurope;
    public static TextureRegion northernEurope;
    public static TextureRegion scandinavia;
    public static TextureRegion ukraine;

    public static TextureRegion westernAustralia;
    public static TextureRegion easternAustralia;
    public static TextureRegion newGuinea;
    public static TextureRegion indonesia;

    public static TextureRegion middleEast;
    public static TextureRegion india;
    public static TextureRegion afghanistan;
    public static TextureRegion siam;
    public static TextureRegion china;
    public static TextureRegion japan;
    public static TextureRegion mongolia;
    public static TextureRegion ural;
    public static TextureRegion siberia;
    public static TextureRegion irkutsk;
    public static TextureRegion yakutsk;
    public static TextureRegion kamchatka;

    public static void load() {
        if (world == null) {
            world = new TextureAtlas(Gdx.files.internal("image/gen/world.atlas"));

            alaska = world.findRegion("alaska");
            northwestTerritory = world.findRegion("northwest-territory");
            alberta = world.findRegion("alberta");
            ontario = world.findRegion("ontario");
            quebec = world.findRegion("quebec");
            easternUS = world.findRegion("eastern-us");
            westernUS = world.findRegion("western-us");
            centralAmerica = world.findRegion("central-america");
            greenland = world.findRegion("greenland");

            venezuela = world.findRegion("venezuela");
            brazil = world.findRegion("brazil");
            peru = world.findRegion("peru");
            argentina = world.findRegion("argentina");

            madagascar = world.findRegion("madagascar");
            southAfrica = world.findRegion("south-africa");
            congo = world.findRegion("congo");
            eastAfrica = world.findRegion("east-africa");
            westAfrica = world.findRegion("west-africa");
            egypt = world.findRegion("egypt");

            iceland = world.findRegion("iceland");
            greatBritain = world.findRegion("great-britain");
            westernEurope = world.findRegion("western-europe");
            southernEurope = world.findRegion("southern-europe");
            northernEurope = world.findRegion("northern-europe");
            scandinavia = world.findRegion("scandinavia");
            ukraine = world.findRegion("ukraine");

            westernAustralia = world.findRegion("western-australia");
            easternAustralia = world.findRegion("eastern-australia");
            newGuinea = world.findRegion("new-guinea");
            indonesia = world.findRegion("indonesia");

            middleEast = world.findRegion("middle-east");
            india = world.findRegion("india");
            afghanistan = world.findRegion("afghanistan");
            siam = world.findRegion("siam");
            china = world.findRegion("china");
            japan = world.findRegion("japan");
            mongolia = world.findRegion("mongolia");
            ural = world.findRegion("ural");
            siberia = world.findRegion("siberia");
            irkutsk = world.findRegion("irkutsk");
            yakutsk = world.findRegion("yakutsk");
            kamchatka = world.findRegion("kamchatka");
        }
    }

    public static void dispose() {
        if (world != null) {
            world.dispose();
            world = null;
        }
    }
}
