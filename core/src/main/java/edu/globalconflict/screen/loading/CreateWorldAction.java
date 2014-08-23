package edu.globalconflict.screen.loading;

import com.badlogic.gdx.graphics.Color;
import edu.globalconflict.Constants;
import edu.globalconflict.GameAssets;
import edu.globalconflict.builder.EntityBuilder;
import edu.globalconflict.component.territory.Army;
import edu.globalconflict.component.Player;
import edu.globalconflict.component.TintColor;
import edu.globalconflict.component.game.*;
import edu.globalconflict.component.territory.TerritoryBounds;
import edu.globalconflict.component.territory.TerritorySelected;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;

import java.util.*;

/**
 * @author mateusz
 * @since 15.08.14
 */
public final class CreateWorldAction implements Runnable, Constants {
    private final EntityManager entityManager;
    private final EntityBuilder entityBuilder;

    public CreateWorldAction(EntityManager entityManager) {
        this.entityManager = entityManager;
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
        initializeTerritories();

        final List<Player> players = Arrays.asList(
                new Player("Player 1", Color.BLUE),
                new Player("Player 2", Color.RED)
        );
        assignTerritoriesToPlayers(players);

        // END TURN action at the beginning of the game sets some labels, adds troops and so on.
        // This is pretty much required setup.
        final EndTurnAction endTurnAction = new EndTurnAction();
        endTurnAction.isNew = true;

        entityBuilder
                .newEntity(Tag.Namespace.GAME, Constants.GAME_ENTITY)
                .withComponent(new CurrentPlayer(players))
                .withComponent(new SelectedTerritoriesStack())

                // game events
                .withComponent(new PlayerClick())
                .withComponent(new AttackAction())
                .withComponent(new TransferAction())
                .withComponent(endTurnAction);
    }

    private void initializeTerritories() {
        // TODO: replace with Territory.class
        final List<UUID> territories = new ArrayList<>(entityManager.getEntitiesForType(TerritoryBounds.class));
        for (UUID territoryEntity : territories) {
            // add some components required for all territories
            entityManager.addComponent(territoryEntity, new TerritorySelected());
            entityManager.addComponent(territoryEntity, new TintColor());
            entityManager.addComponent(territoryEntity, new Army());
        }
    }

    /**
     * Randomly assigns territories to players.
     * @param players Players list (ArrayList, for performance)
     */
    private void assignTerritoriesToPlayers(List<Player> players) {
        // TODO: replace with Territory.class
        final List<UUID> territories = new ArrayList<>(entityManager.getEntitiesForType(TerritoryBounds.class));
        Collections.shuffle(territories);

        final int numOfPlayers = players.size();
        int i = 0;
        for (UUID territoryEntity : territories) {
            final int ownerIndex = i++ % numOfPlayers;
            // this is O(1), because of ArrayList
            final Player owner = players.get(ownerIndex);

            // add owner to the territory, change tint color
            entityManager.addComponent(territoryEntity, owner);
            entityManager.getComponent(territoryEntity, TintColor.class).color.set(owner.color);
        }
    }

    private void constructAustralia() {
        entityBuilder
                .newEntity()
                .withTerritory(WESTERN_AUSTRALIA, EASTERN_AUSTRALIA, INDONESIA, NEW_GUINEA)
                .withTexture(GameAssets.westernAustralia)
                .withPosition(2474, 1300)
                .withBounds(20, 99, 174, 0, 186, 12, 152, 136, 272, 147, 232, 269, 177, 202, 18, 238)

                .newEntity()
                .withTexture(GameAssets.easternAustralia)
                .withTerritory(EASTERN_AUSTRALIA, WESTERN_AUSTRALIA, NEW_GUINEA)
                .withPosition(2630, 1362)
                .withBounds(1, 164, 33, 47, 54, 18, 110, 20, 101, 45, 142, 78, 178, 3, 255, 163, 210, 249, 98, 366,
                        80, 303, 122, 176)

                .newEntity()
                .withTexture(GameAssets.newGuinea)
                .withTerritory(NEW_GUINEA, EASTERN_AUSTRALIA, INDONESIA, WESTERN_AUSTRALIA)
                .withPosition(2657, 999)
                .withBounds(14, 1, 85, 64, 115, 45, 199, 87, 239, 57, 338, 128, 339, 154, 252, 96, 238, 109, 242, 150,
                        204, 143, 173, 115, 157, 134, 107, 120, 121, 103, 5, 65, 0, 25)

                .newEntity()
                .withTexture(GameAssets.indonesia)
                .withTerritory(INDONESIA, NEW_GUINEA, WESTERN_AUSTRALIA, SIAM)
                .withPosition(2326, 988)
                .withBounds(1, 143, 84, 199, 148, 188, 224, 132, 247, 2, 264, 1, 326, 130, 312, 197, 328, 307, 300, 321,
                        257, 321, 106, 288);
    }

    private void constructAsia() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.middleEast)
                .withPosition(1602, 733)
                .withBounds(15, 5, 147, 8, 259, 62, 289, 44, 351, 68, 376, 175, 317, 277, 204, 330, 90, 123, 102, 61,
                        23, 61)

                .newEntity()
                .withTexture(GameAssets.india)
                .withPosition(1948, 806)
                .withBounds(2, 49, 91, 3, 187, 46, 178, 78, 317, 127, 346, 103, 375, 117, 335, 195, 319, 179, 219, 290,
                        246, 356, 229, 364, 188, 336, 140, 224, 72, 150, 25, 149, 33, 115)

                .newEntity()
                .withTexture(GameAssets.afghanistan)
                .withPosition(1788, 473)
                .withBounds(2, 64, 127, 50, 113, 18, 179, 1, 276, 37, 332, 99, 313, 116, 322, 140, 224, 196, 202, 189,
                        171, 212, 72, 161, 44, 115, 63, 102)

                .newEntity()
                .withTexture(GameAssets.siam)
                .withPosition(2279, 860)
                .withBounds(47, 0, 77, 72, 135, 56, 159, 75, 143, 102, 188, 167, 149, 216, 141, 300, 76, 224, 79, 172,
                        4, 80)

                .newEntity()
                .withTexture(GameAssets.china)
                .withPosition(2061, 671)
                .withBounds(90, 1, 310, 127, 397, 71, 435, 101, 419, 116, 474, 133, 452, 160, 498, 222, 516, 281,
                        508, 301, 400, 346, 305, 303, 277, 239, 226, 217, 204, 232, 71, 193, 79, 164, 3, 107, 57, 69)

                .newEntity()
                .withTexture(GameAssets.japan)
                .withPosition(2602, 528)
                .withBounds(0, 4, 9, 0, 58, 44, 113, 114, 115, 190, 39, 249, 22, 226, 27, 208, 90, 170, 64, 119, 71, 106)

                .newEntity()
                .withTexture(GameAssets.mongolia)
                .withPosition(2154, 488)
                .withBounds(7, 47, 85, 22, 197, 48, 271, 44, 262, 8, 310, 8, 405, 71, 426, 58, 429, 144, 414, 159,
                        457, 202, 432, 209, 380, 154, 290, 124, 231, 172, 131, 139)

                .newEntity()
                .withTexture(GameAssets.ural)
                .withPosition(1834, 352)
                .withBounds(54, 0, 117, 5, 293, 153, 260, 163, 295, 202, 270, 222, 304, 246, 291, 264, 189, 180,
                        58, 185, 0, 85, 38, 56, 35, 14)

                .newEntity()
                .withTexture(GameAssets.siberia)
                .withPosition(1951, 331)
                .withBounds(1, 44, 121, 1, 214, 23, 296, 175, 250, 190, 256, 228, 196, 254, 202, 271, 160, 263,
                        182, 240, 148, 204, 179, 198)

                .newEntity()
                .withTexture(GameAssets.irkutsk)
                .withPosition(2151, 328)
                .withBounds(80, 0, 126, 49, 199, 44, 235, 77, 350, 91, 387, 159, 357, 150, 322, 112, 258, 113, 270, 145,
                        134, 141, 88, 121, 75, 143, 5, 139, 62, 111, 56, 72, 105, 61, 75, 11)

                .newEntity()
                .withTexture(GameAssets.yakutsk)
                .withPosition(2167, 252)
                .withBounds(1, 1, 416, 39, 396, 79, 335, 101, 300, 141, 309, 171, 221, 163, 155, 120, 113, 136, 13, 42,
                        33, 22)

                .newEntity()
                .withTexture(GameAssets.kamchatka)
                .withPosition(2469, 396)
                .withBounds(116, 4, 215, 1, 378, 49, 296, 98, 291, 192, 277, 193, 219, 130, 175, 106, 88, 108, 66, 153,
                        154, 204, 147, 278, 117, 281, 108, 263, 130, 246, 110, 217, 90, 230, 57, 190, 68, 165, 0, 126);
    }

    private void constructAfrica() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.madagascar)
                .withPosition(1787, 1161)
                .withBounds(1, 110, 16, 48, 69, 2, 78, 36, 41, 145, 16, 154)

                .newEntity()
                .withTexture(GameAssets.southAfrica)
                .withPosition(1472, 1262)
                .withBounds(6, 1, 102, 15, 106, 57, 173, 80, 293, 58, 292, 112, 131, 320, 62, 311, 1, 119)

                .newEntity()
                .withTexture(GameAssets.congo)
                .withPosition(1444, 1017)
                .withBounds(8, 103, 72, 103, 54, 58, 143, 8, 194, 77, 265, 98, 208, 184, 189, 254, 139, 245, 138, 204,
                        33, 191)

                .newEntity()
                .withTexture(GameAssets.eastAfrica)
                .withPosition(1580, 1053)
                .withBounds(25, 13, 133, 5, 223, 138, 292, 130, 255, 219, 167, 327, 182, 373, 122, 383, 77, 312,
                        133, 241, 125, 210, 61, 201, 2, 103)

                .newEntity()
                .withTexture(GameAssets.westAfrica)
                .withPosition(1178, 847)
                .withBounds(127, 22, 281, 6, 275, 135, 420, 199, 406, 288, 318, 341, 333, 386, 274, 388, 204, 344,
                        90, 363, 7, 272, 9, 175)

                .newEntity()
                .withTexture(GameAssets.egypt)
                .withPosition(1451, 652)
                .withBounds(21, 0, 92, 35, 111, 4, 243, 35, 252, 120, 151, 123, 151, 146, 3, 82);
    }

    private void constructEurope() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.iceland)
                .withPosition(1192, 180)
                .withBounds(7, 10, 65, 1, 80, 14, 29, 29, 1, 21)

                .newEntity()
                .withTexture(GameAssets.greatBritain)
                .withPosition(1278, 314)
                .withBounds(1, 41, 56, 0, 103, 57, 99, 77, 42, 82, 5, 75)

                .newEntity()
                .withTexture(GameAssets.westernEurope)
                .withPosition(1271, 468)
                .withBounds(7, 82, 72, 83, 50, 28, 114, 3, 162, 26, 157, 80, 88, 120, 66, 157, 0, 150)

                .newEntity()
                .withTexture(GameAssets.southernEurope)
                .withPosition(1421, 468)
                .withBounds(5, 18, 92, 10, 85, 21, 109, 38, 149, 12, 191, 13, 211, 43, 154, 134, 115, 96, 80, 136,
                        53, 122, 87, 102, 17, 54)

                .newEntity()
                .withTexture(GameAssets.northernEurope)
                .withPosition(1393, 364)
                .withBounds(2, 66, 62, 2, 68, 38, 163, 25, 187, 73, 159, 123, 121, 118, 120, 93, 62, 112, 37, 106)

                .newEntity()
                .withTexture(GameAssets.scandinavia)
                .withPosition(1413, 259)
                .withBounds(4, 91, 127, 5, 198, 6, 181, 20, 206, 79, 189, 103, 112, 107, 71, 154, 21, 128)

                .newEntity()
                .withTexture(GameAssets.ukraine)
                .withPosition(1547, 450)
                .withBounds(67, 8, 166, 28, 286, 9, 318, 32, 282, 61, 356, 197, 260, 195, 239, 223, 282, 319,
                        250, 331, 152, 260, 25, 229, 3, 130, 80, 77, 50, 16);
    }

    private void constructSouthAmerica() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.venezuela)
                .withPosition(533, 925)
                .withBounds(1, 124, 79, 2, 287, 91, 209, 125, 193, 85, 101, 138, 101, 189)

                .newEntity()
                .withTexture(GameAssets.brazil)
                .withPosition(598, 1247)
                .withBounds(1, 133, 132, 2, 234, 33, 397, 122, 329, 309, 244, 419, 194, 381, 219, 334, 140, 202,
                        73, 154)

                .newEntity()
                .withTexture(GameAssets.peru)
                .withPosition(514, 1181)
                .withBounds(21, 2, 108, 51, 81, 94, 243, 190, 294, 281, 282, 317, 206, 256, 158, 262, 7, 77)

                .newEntity()
                .withTexture(GameAssets.argentina)
                .withPosition(639, 1487)
                .withBounds(8, 1, 34, 61, 80, 46, 198, 170, 124, 323, 170, 406, 120, 401, 38, 314);
    }

    private void constructNorthAmerica() {
        entityBuilder
                .newEntity()
                .withTexture(GameAssets.alaska)
                .withPosition(0, 263)
                .withBounds(0, 148, 44, 85, 122, 24, 335, 10, 225, 115)

                .newEntity()
                .withTexture(GameAssets.northwestTerritory)
                .withPosition(228, 207)
                .withBounds(0, 137, 109, 46, 291, 2, 510, 10, 539, 55, 365, 137)

                .newEntity()
                .withTexture(GameAssets.alberta)
                .withPosition(234, 327)
                .withBounds(26, 119, 0, 88, 28, 15, 1, 4, 290, 2, 220, 119)

                .newEntity()
                .withTexture(GameAssets.ontario)
                .withPosition(457, 403)
                .withBounds(1, 118, 69, 1, 135, 1, 138, 30, 201, 53, 182, 140, 219, 160, 125, 193, 166, 157, 118, 120)

                .newEntity()
                .withTexture(GameAssets.quebec)
                .withPosition(642, 387)
                .withBounds(0, 161, 107, 2, 197, 26, 236, 92, 139, 140, 173, 168, 112, 198, 97, 158)

                .newEntity()
                .withTexture(GameAssets.easternUS)
                .withPosition(304, 591)
                .withBounds(169, 1, 285, 26, 277, 82, 434, 19, 246, 198, 244, 259, 216, 208, 79, 250, 4, 190, 43, 190,
                        57, 138, 153, 139)

                .newEntity()
                .withTexture(GameAssets.westernUS)
                .withPosition(177, 525)
                .withBounds(84, 2, 292, 2, 292, 35, 275, 134, 181, 135, 165, 186, 34, 181, 1, 110)

                .newEntity()
                .withTexture(GameAssets.centralAmerica)
                .withPosition(206, 790)
                .withBounds(5, 1, 140, 31, 177, 73, 179, 148, 322, 98, 480, 155, 290, 182, 351, 265, 304, 271, 80, 139,
                        23, 79)

                .newEntity()
                .withTexture(GameAssets.greenland)
                .withPosition(914, 208)
                .withBounds(1, 40, 106, 9, 388, 12, 292, 118, 99, 207, 56, 179, 90, 117, 60, 57);
    }
}
