package edu.globalconflict.screen.action;

import com.badlogic.gdx.graphics.Color;
import edu.globalconflict.GameAssets;
import edu.globalconflict.builder.EntityBuilder;
import edu.globalconflict.component.PlayerClick;
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

        // create one player entity
        entityBuilder
                .newEntity(Tag.Namespace.PLAYER, "Player 1")
                .withComponent(new PlayerClick());
    }

    private void constructAustralia() {
    }

    private void constructAsia() {
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
                .withPosition(1187, 847)

                .newEntity()
                .withTexture(GameAssets.egypt)
                .withPosition(1451, 652);
    }

    private void constructEurope() {
        // iceland -- 1192, 180
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

                .newEntity()
                .withTexture(GameAssets.northwestTerritory)
                .withPosition(228, 207)

                .newEntity()
                .withTexture(GameAssets.alberta)
                .withPosition(234, 328)

                .newEntity()
                .withTexture(GameAssets.ontario)
                .withPosition(457, 403)

                .newEntity()
                .withTexture(GameAssets.quebec)
                .withPosition(642, 387)

                .newEntity()
                .withTexture(GameAssets.easternUS)
                .withPosition(304, 591)

                .newEntity()
                .withTexture(GameAssets.westernUS)
                .withPosition(177, 525)

                .newEntity()
                .withTexture(GameAssets.centralAmerica)
                .withPosition(206, 790)

                .newEntity()
                .withTexture(GameAssets.greenland)
                .withPosition(914, 208);
    }
}
