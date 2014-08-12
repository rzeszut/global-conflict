package edu.globalconflict.model.builder;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.globalconflict.Constants;
import edu.globalconflict.model.GameEntity;
import edu.globalconflict.model.component.GenericRenderComponent;

/**
 * @author mateusz
 * @since 12.08.14
 */
public abstract class AbstractEntityBuilder<B extends AbstractEntityBuilder<B>> {
    protected GameEntity item = new GameEntity();

    public B withTextureRegion(TextureRegion region) {
        item.area.width = region.getRegionWidth();
        item.area.height = region.getRegionHeight();
        item.renderComponent = new GenericRenderComponent(region);
        return self();
    }

    public B withPosition(float x, float y) {
        item.area.x = x;
        item.area.y = Constants.WORLD_HEIGHT - y;
        return self();
    }

    public GameEntity build() {
        return item;
    }

    protected abstract B self();
}
