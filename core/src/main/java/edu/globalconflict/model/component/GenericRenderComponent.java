package edu.globalconflict.model.component;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.globalconflict.model.GameEntity;

/**
 * @author mateusz
 * @since 12.08.14
 */
public final class GenericRenderComponent implements RenderComponent {
    private TextureRegion region;

    public GenericRenderComponent(TextureRegion region) {
        this.region = region;
    }

    @Override
    public void render(GameEntity entity, Batch batch) {
        batch.draw(region, entity.area.x, entity.area.y, entity.area.width, entity.area.height);
    }
}
