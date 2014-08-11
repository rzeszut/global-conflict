package edu.globalconflict.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import edu.globalconflict.GameAssets;
import edu.globalconflict.Constants;

/**
 * @author mateusz
 * @since 10.08.14
 */
public class World extends Group {
    public World() {
        setTouchable(Touchable.childrenOnly);

        constructAmerica();
    }

    private void constructAmerica() {
        final Territory alaska = newTerritory(GameAssets.alaska);
        alaska.name = "Alaska";
        setTerritoryPosition(alaska, 0, 106);

        final Territory northwestTerritory = newTerritory(GameAssets.northwestTerritory);
        northwestTerritory.name = "Northwest Territory";
        setTerritoryPosition(northwestTerritory, 232, 48);

        final Territory alberta = newTerritory(GameAssets.alberta);
        alberta.name = "Alberta";
        setTerritoryPosition(alberta, 239, 171);

        final Territory ontario = newTerritory(GameAssets.ontario);
        ontario.name = "Ontario";
        setTerritoryPosition(ontario, 466, 247);

        final Territory quebec = newTerritory(GameAssets.quebec);
        quebec.name = "Quebec";
        setTerritoryPosition(quebec, 656, 231);

        final Territory easternUS = newTerritory(GameAssets.easternUS);
        easternUS.name = "Eastern United States";
        setTerritoryPosition(easternUS, 311, 439);

        final Territory westernUS = newTerritory(GameAssets.westernUS);
        westernUS.name = "Western United States";
        setTerritoryPosition(westernUS, 181, 372);

        final Territory centralAmerica = newTerritory(GameAssets.centralAmerica);
        centralAmerica.name = "Central America";
        setTerritoryPosition(centralAmerica, 211, 642);

        final Territory greenland = newTerritory(GameAssets.greenland);
        greenland.name = "Greenland";
        setTerritoryPosition(greenland, 933, 0);
    }

    private Territory newTerritory(TextureRegion region) {
        final Territory territory = new Territory();
        territory.texture = region;
        territory.setWidth(region.getRegionWidth());
        territory.setHeight(region.getRegionHeight());

        addActor(territory);
        return territory;
    }

    private static void setTerritoryBounds(Territory territory, float... indices) {
        assert indices.length >= 6;
        assert indices.length % 2 == 0;

        final int count = indices.length / 2;
        final Array<Vector2> bounds = new Array<Vector2>(count);

        for (int i = 0; i < count; ++i) {
            float x = indices[2 * i], y = indices[2 * i + 1];
            bounds.insert(i, new Vector2(x, territory.getWidth() - y));
        }

        territory.polygonBounds = bounds;
    }

    /**
     * Helper method for setting position in Gimp-coordinates (right-left corner to bottom-left corner coords).
     *
     * @param territory Territory
     * @param x         gimp x
     * @param y         gimp y
     */
    private static void setTerritoryPosition(Territory territory, float x, float y) {
        territory.setPosition(x, Constants.WORLD_HEIGHT - y);
    }
}
