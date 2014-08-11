package edu.globalconflict.actor;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import edu.globalconflict.Assets;
import edu.globalconflict.Constants;
import org.apache.commons.lang3.text.WordUtils;

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
        final Territory alaska = newTerritory(Assets.america, "alaska");
        setTerritoryPosition(alaska, 0, 106);

        final Territory northwestTerritory = newTerritory(Assets.america, "northwest-territory");
        setTerritoryPosition(northwestTerritory, 232, 48);

        final Territory alberta = newTerritory(Assets.america, "alberta");
        setTerritoryPosition(alberta, 239, 171);

        final Territory ontario = newTerritory(Assets.america, "ontario");
        setTerritoryPosition(ontario, 466, 247);

        final Territory quebec = newTerritory(Assets.america, "quebec");
        setTerritoryPosition(quebec, 656, 231);

        final Territory easternUS = newTerritory(Assets.america, "eastern-us");
        setTerritoryPosition(easternUS, 311, 439);

        final Territory westernUS = newTerritory(Assets.america, "western-us");
        setTerritoryPosition(westernUS, 181, 372);

        final Territory centralAmerica = newTerritory(Assets.america, "central-america");
        setTerritoryPosition(centralAmerica, 211, 642);

        final Territory greenland = newTerritory(Assets.america, "greenland");
        setTerritoryPosition(greenland, 933, 0);
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

    private Territory newTerritory(TextureAtlas atlas, String name) {
        final Territory territory = new Territory();
        final TextureRegion region = atlas.findRegion(name);
        territory.texture = region;
        territory.setWidth(region.getRegionWidth());
        territory.setHeight(region.getRegionHeight());
        territory.name = WordUtils.capitalize(name.replace('-', ' '));

        addActor(territory);
        return territory;
    }

    /**
     * Helper method for setting position in Gimp-coordinates.
     *
     * @param territory Territory
     * @param x         gimp x
     * @param y         gimp y
     */
    private static void setTerritoryPosition(Territory territory, float x, float y) {
        territory.setPosition(x, Constants.WORLD_HEIGHT - y);
    }
}
