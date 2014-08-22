package edu.globalconflict.screen.action;

import edu.globalconflict.Constants;
import edu.globalconflict.GameAssets;
import edu.globalconflict.builder.EntityBuilder;
import edu.globalconflict.component.TintColor;
import edu.globalconflict.component.game.PlayerClick;
import edu.globalconflict.component.game.SelectedTerritoriesStack;
import edu.globalconflict.component.territory.TerritorySelected;
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
                .withBounds(20, 99, 174, 0, 186, 12, 152, 136, 272, 147, 232, 269, 177, 202, 18, 238)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.easternAustralia)
                .withPosition(2630, 1362)
                .withBounds(1, 164, 33, 47, 54, 18, 110, 20, 101, 45, 142, 78, 178, 3, 255, 163, 210, 249, 98, 366,
                        80, 303, 122, 176)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.newGuinea)
                .withPosition(2657, 999)
                .withBounds(14, 1, 85, 64, 115, 45, 199, 87, 239, 57, 338, 128, 339, 154, 252, 96, 238, 109, 242, 150,
                        204, 143, 173, 115, 157, 134, 107, 120, 121, 103, 5, 65, 0, 25)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.indonesia)
                .withPosition(2326, 988)
                .withBounds(1, 143, 84, 199, 148, 188, 224, 132, 247, 2, 264, 1, 326, 130, 312, 197, 328, 307, 300, 321,
                        257, 321, 106, 288)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor());
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
                .withBounds(1, 110, 16, 48, 69, 2, 78, 36, 41, 145, 16, 154)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.southAfrica)
                .withPosition(1472, 1262)
                .withBounds(6, 1, 102, 15, 106, 57, 173, 80, 293, 58, 292, 112, 131, 320, 62, 311, 1, 119)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.congo)
                .withPosition(1444, 1017)
                .withBounds(8, 103, 72, 103, 54, 58, 143, 8, 194, 77, 265, 98, 208, 184, 189, 254, 139, 245, 138, 204,
                        33, 191)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.eastAfrica)
                .withPosition(1580, 1053)
                .withBounds(25, 13, 133, 5, 223, 138, 292, 130, 255, 219, 167, 327, 182, 373, 122, 383, 77, 312,
                        133, 241, 125, 210, 61, 201, 2, 103)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.westAfrica)
                .withPosition(1178, 847)
                .withBounds(127, 22, 281, 6, 275, 135, 420, 199, 406, 288, 318, 341, 333, 386, 274, 388, 204, 344,
                        90, 363, 7, 272, 9, 175)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.egypt)
                .withPosition(1451, 652)
                .withBounds(21, 0, 92, 35, 111, 4, 243, 35, 252, 120, 151, 123, 151, 146, 3, 82)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor());
    }

    private void constructEurope() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.iceland)
                .withPosition(1192, 180)
                .withBounds(7, 10, 65, 1, 80, 14, 29, 29, 1, 21)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.greatBritain)
                .withPosition(1278, 314)
                .withBounds(1, 41, 56, 0, 103, 57, 99, 77, 42, 82, 5, 75)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.westernEurope)
                .withPosition(1271, 468)
                .withBounds(7, 82, 72, 83, 50, 28, 114, 3, 162, 26, 157, 80, 88, 120, 66, 157, 0, 150)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.southernEurope)
                .withPosition(1421, 468)
                .withBounds(5, 18, 92, 10, 85, 21, 109, 38, 149, 12, 191, 13, 211, 43, 154, 134, 115, 96, 80, 136,
                        53, 122, 87, 102, 17, 54)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.northernEurope)
                .withPosition(1393, 364)
                .withBounds(2, 66, 62, 2, 68, 38, 163, 25, 187, 73, 159, 123, 121, 118, 120, 93, 62, 112, 37, 106)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.scandinavia)
                .withPosition(1413, 259)
                .withBounds(4, 91, 127, 5, 198, 6, 181, 20, 206, 79, 189, 103, 112, 107, 71, 154, 21, 128)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.ukraine)
                .withPosition(1547, 450)
                .withBounds(67, 8, 166, 28, 286, 9, 318, 32, 282, 61, 356, 197, 260, 195, 239, 223, 282, 319,
                        250, 331, 152, 260, 25, 229, 3, 130, 80, 77, 50, 16)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor());
    }

    private void constructSouthAmerica() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.venezuela)
                .withPosition(533, 925)
                .withBounds(1, 124, 79, 2, 287, 91, 209, 125, 193, 85, 101, 138, 101, 189)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.brazil)
                .withPosition(598, 1247)
                .withBounds(1, 133, 132, 2, 234, 33, 397, 122, 329, 309, 244, 419, 194, 381, 219, 334, 140, 202,
                        73, 154)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.peru)
                .withPosition(514, 1181)
                .withBounds(21, 2, 108, 51, 81, 94, 243, 190, 294, 281, 282, 317, 206, 256, 158, 262, 7, 77)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor())

                .newEntity()
                .withTexture(GameAssets.argentina)
                .withPosition(639, 1487)
                .withBounds(8, 1, 34, 61, 80, 46, 198, 170, 124, 323, 170, 406, 120, 401, 38, 314)
                .withComponent(new TerritorySelected())
                .withComponent(new TintColor());
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
