package edu.globalconflict.screen.action;

import com.badlogic.gdx.graphics.Color;
import edu.globalconflict.Constants;
import edu.globalconflict.GameAssets;
import edu.globalconflict.builder.EntityBuilder;
import edu.globalconflict.component.game.PlayerClick;
import edu.globalconflict.component.territory.TerritorySelected;
import edu.globalconflict.component.TintColor;
import edu.globalconflict.component.game.SelectedTerritoriesStack;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;

/**
 * @author mateusz
 * @since 15.08.14
 */
public final class CreateWorldAction implements Runnable {
    private EntityBuilder entityBuilder;

    public CreateWorldAction(EntityManager entityManager) {
        this.entityBuilder = new EntityBuilder(entityManager);
    }

    @Override
    public void run() {
        constructNorthAmerica();
        constructSouthAmerica();
        constructEurope();
        constructAfrica();
        constructAsia();
        constructAustralia();

        entityBuilder
                .newEntity(Tag.Namespace.GAME, Constants.GAME_ENTITY)
                .withComponent(new PlayerClick())
                .withComponent(new SelectedTerritoriesStack());
    }

    private void constructAustralia() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.westernAustralia)
                .withPosition(2474, 1300)

                .newEntity()
                .withTexture(GameAssets.easternAustralia)
                .withPosition(2630, 1362)

                .newEntity()
                .withTexture(GameAssets.newGuinea)
                .withPosition(2657, 999)

                .newEntity()
                .withTexture(GameAssets.indonesia)
                .withPosition(2326, 988);
    }

    private void constructAsia() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.middleEast)
                .withPosition(1602, 733)

                .newEntity()
                .withTexture(GameAssets.india)
                .withPosition(1948, 806)

                .newEntity()
                .withTexture(GameAssets.afghanistan)
                .withPosition(1788, 473)

                .newEntity()
                .withTexture(GameAssets.siam)
                .withPosition(2279, 939)

                .newEntity()
                .withTexture(GameAssets.china)
                .withPosition(2061, 671)

                .newEntity()
                .withTexture(GameAssets.japan)
                .withPosition(2602, 528)

                .newEntity()
                .withTexture(GameAssets.mongolia)
                .withPosition(2154, 488)

                .newEntity()
                .withTexture(GameAssets.ural)
                .withPosition(1834, 352)

                .newEntity()
                .withTexture(GameAssets.siberia)
                .withPosition(1951, 331)

                .newEntity()
                .withTexture(GameAssets.irkutsk)
                .withPosition(2151, 328)

                .newEntity()
                .withTexture(GameAssets.yakutsk)
                .withPosition(2167, 252)

                .newEntity()
                .withTexture(GameAssets.kamchatka)
                .withPosition(2469, 396);
    }

    private void constructAfrica() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.madagascar)
                .withPosition(1787, 1161)

                .newEntity()
                .withTexture(GameAssets.southAfrica)
                .withPosition(1472, 1262)

                .newEntity()
                .withTexture(GameAssets.congo)
                .withPosition(1444, 1017)

                .newEntity()
                .withTexture(GameAssets.eastAfrica)
                .withPosition(1580, 1053)

                .newEntity()
                .withTexture(GameAssets.westAfrica)
                .withPosition(1178, 847)

                .newEntity()
                .withTexture(GameAssets.egypt)
                .withPosition(1451, 652);
    }

    private void constructEurope() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.iceland)
                .withPosition(1192, 180)

                .newEntity()
                .withTexture(GameAssets.greatBritain)
                .withPosition(1278, 314)

                .newEntity()
                .withTexture(GameAssets.westernEurope)
                .withPosition(1271, 468)

                .newEntity()
                .withTexture(GameAssets.southernEurope)
                .withPosition(1421, 468)

                .newEntity()
                .withTexture(GameAssets.northernEurope)
                .withPosition(1393, 364)

                .newEntity()
                .withTexture(GameAssets.scandinavia)
                .withPosition(1413, 259)

                .newEntity()
                .withTexture(GameAssets.ukraine)
                .withPosition(1547, 450);
    }

    private void constructSouthAmerica() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.venezuela)
                .withTintColor(Color.MAGENTA)
                .withPosition(533, 925)

                .newEntity()
                .withTexture(GameAssets.brazil)
                .withPosition(598, 1247)

                .newEntity()
                .withTexture(GameAssets.peru)
                .withPosition(514, 1181)

                .newEntity()
                .withTexture(GameAssets.argentina)
                .withPosition(639, 1487);
    }

    private void constructNorthAmerica() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.alaska)
                .withPosition(0, 263)
                .withBounds(0, 148, 44, 85, 122, 24, 335, 10, 225, 115)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.northwestTerritory)
                .withPosition(228, 207)
                .withBounds(0, 137, 109, 46, 291, 2, 510, 10, 539, 55, 365, 137)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.alberta)
                .withPosition(234, 327)
                .withBounds(26, 119, 0, 88, 28, 15, 1, 4, 290, 2, 220, 119)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.ontario)
                .withPosition(457, 403)
                .withBounds(1, 118, 69, 1, 135, 1, 138, 30, 201, 53, 182, 140, 219, 160, 125, 193, 166, 157, 118, 120)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.quebec)
                .withPosition(642, 387)
                .withBounds(0, 161, 107, 2, 197, 26, 236, 92, 139, 140, 173, 168, 112, 198, 97, 158)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.easternUS)
                .withPosition(304, 591)
                .withBounds(169, 1, 285, 26, 277, 82, 434, 19, 246, 198, 244, 259, 216, 208, 79, 250, 4, 190, 43, 190,
                        57, 138, 153, 139)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.westernUS)
                .withPosition(177, 525)
                .withBounds(84, 2, 292, 2, 292, 35, 275, 134, 181, 135, 165, 186, 34, 181, 1, 110)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.centralAmerica)
                .withPosition(206, 790)
                .withBounds(5, 1, 140, 31, 177, 73, 179, 148, 322, 98, 480, 155, 290, 182, 351, 265, 304, 271, 80, 139,
                        23, 79)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.greenland)
                .withPosition(914, 208)
                .withBounds(1, 40, 106, 9, 388, 12, 292, 118, 99, 207, 56, 179, 90, 117, 60, 57)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor());
    }
}
