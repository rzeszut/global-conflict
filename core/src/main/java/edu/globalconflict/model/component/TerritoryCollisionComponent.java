package edu.globalconflict.model.component;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import edu.globalconflict.model.GameEntity;

/**
 * @author mateusz
 * @since 12.08.14
 */
public final class TerritoryCollisionComponent implements CollisionComponent {
    private Array<Vector2> polygonBounds;
    /**
     * Temporary variable used in TerritoryCollisionComponent#hit() method.
     */
    private Vector2 temp;

    public TerritoryCollisionComponent(Array<Vector2> polygonBounds) {
        this.polygonBounds = polygonBounds;
        this.temp = new Vector2();
    }

    @Override
    public boolean hit(GameEntity entity, float x, float y) {
        if (!entity.area.contains(x, y)) {
            return false;
        }

        temp.set(x - entity.area.x, y - entity.area.y);
        return Intersector.isPointInPolygon(polygonBounds, temp);
    }
}
