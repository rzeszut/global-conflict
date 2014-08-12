package edu.globalconflict.model;

import edu.globalconflict.model.component.CollisionComponent;
import edu.globalconflict.model.component.RenderComponent;
import edu.globalconflict.util.Rect;

/**
 * @author mateusz
 * @since 12.08.14
 */
public final class GameEntity {
    public Rect area = new Rect();

    public RenderComponent renderComponent = RenderComponent.NULL_OBJECT;
    public CollisionComponent collisionComponent = CollisionComponent.NULL_OBJECT;
}
