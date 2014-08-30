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

    public static TextureAtlas.AtlasRegion alaska;
    public static TextureAtlas.AtlasRegion northwestTerritory;
    public static TextureAtlas.AtlasRegion alberta;
    public static TextureAtlas.AtlasRegion ontario;
    public static TextureAtlas.AtlasRegion quebec;
    public static TextureAtlas.AtlasRegion easternUS;
    public static TextureAtlas.AtlasRegion westernUS;
    public static TextureAtlas.AtlasRegion centralAmerica;
    public static TextureAtlas.AtlasRegion greenland;

    public static TextureAtlas.AtlasRegion venezuela;
    public static TextureAtlas.AtlasRegion brazil;
    public static TextureAtlas.AtlasRegion peru;
    public static TextureAtlas.AtlasRegion argentina;

    public static TextureAtlas.AtlasRegion madagascar;
    public static TextureAtlas.AtlasRegion southAfrica;
    public static TextureAtlas.AtlasRegion congo;
    public static TextureAtlas.AtlasRegion eastAfrica;
    public static TextureAtlas.AtlasRegion northAfrica;
    public static TextureAtlas.AtlasRegion egypt;

    public static TextureAtlas.AtlasRegion iceland;
    public static TextureAtlas.AtlasRegion greatBritain;
    public static TextureAtlas.AtlasRegion westernEurope;
    public static TextureAtlas.AtlasRegion southernEurope;
    public static TextureAtlas.AtlasRegion northernEurope;
    public static TextureAtlas.AtlasRegion scandinavia;
    public static TextureAtlas.AtlasRegion ukraine;

    public static TextureAtlas.AtlasRegion westernAustralia;
    public static TextureAtlas.AtlasRegion easternAustralia;
    public static TextureAtlas.AtlasRegion newGuinea;
    public static TextureAtlas.AtlasRegion indonesia;

    public static TextureAtlas.AtlasRegion middleEast;
    public static TextureAtlas.AtlasRegion india;
    public static TextureAtlas.AtlasRegion afghanistan;
    public static TextureAtlas.AtlasRegion siam;
    public static TextureAtlas.AtlasRegion china;
    public static TextureAtlas.AtlasRegion japan;
    public static TextureAtlas.AtlasRegion mongolia;
    public static TextureAtlas.AtlasRegion ural;
    public static TextureAtlas.AtlasRegion siberia;
    public static TextureAtlas.AtlasRegion irkutsk;
    public static TextureAtlas.AtlasRegion yakutsk;
    public static TextureAtlas.AtlasRegion kamchatka;

    public static BitmapFont armyFont;

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
            northAfrica = world.findRegion("north-africa");
            egypt = world.findRegion("egypt");

            iceland = world.findRegion("iceland");
            greatBritain = world.findRegion("great-britain");
            westernEurope = world.findRegion("western-europe");
            southernEurope = world.findRegion("southern-europe");
            northernEurope = world.findRegion("northern-europe");
            scandinavia = world.findRegion("scandinavia");
            ukraine = world.findRegion("ukraine");
            System.out.println(world.findRegion("ukraine").name);

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
