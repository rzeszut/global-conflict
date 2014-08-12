package edu.globalconflict.model;

import com.badlogic.gdx.utils.Array;
import edu.globalconflict.GameAssets;
import edu.globalconflict.model.builder.TerritoryBuilder;

/**
 * @author mateusz
 * @since 12.08.14
 */
public final class World {
    public Array<GameEntity> entities;

    public World() {
        entities = new Array<>();

        constructAmerica();
    }

    private void constructAmerica() {
        final GameEntity alaska = new TerritoryBuilder()
                .withTextureRegion(GameAssets.alaska)
                .withPosition(0, 106)
                .build();
        entities.add(alaska);

        final GameEntity northwestTerritory = new TerritoryBuilder()
                .withTextureRegion(GameAssets.northwestTerritory)
                .withPosition(232, 48)
                .build();
        entities.add(northwestTerritory);

        final GameEntity alberta = new TerritoryBuilder()
                .withTextureRegion(GameAssets.alberta)
                .withPosition(239, 171)
                .build();
        entities.add(alberta);

        final GameEntity ontario = new TerritoryBuilder()
                .withTextureRegion(GameAssets.ontario)
                .withPosition(466, 247)
                .build();
        entities.add(ontario);

        final GameEntity quebec = new TerritoryBuilder()
                .withTextureRegion(GameAssets.quebec)
                .withPosition(656, 231)
                .build();
        entities.add(quebec);

        final GameEntity easternUS = new TerritoryBuilder()
                .withTextureRegion(GameAssets.easternUS)
                .withPosition(311, 439)
                .build();
        entities.add(easternUS);

        final GameEntity westernUS = new TerritoryBuilder()
                .withTextureRegion(GameAssets.westernUS)
                .withPosition(181, 372)
                .build();
        entities.add(westernUS);

        final GameEntity centralAmerica = new TerritoryBuilder()
                .withTextureRegion(GameAssets.centralAmerica)
                .withPosition(211, 642)
                .build();
        entities.add(centralAmerica);

        final GameEntity greenland = new TerritoryBuilder()
                .withTextureRegion(GameAssets.greenland)
                .withPosition(933, 0)
                .build();
        entities.add(greenland);
    }
}
