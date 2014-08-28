package edu.globalconflict.component.territory;

import edu.globalconflict.entity.Component;

/**
 * Component representing territory data.
 *
 * @author mateusz
 * @since 22.08.14
 */
public final class Territory implements Component {
    /**
     * Name of the territory.
     */
    public String name;
    /**
     * A list of its neighbors (names).
     */
    public String[] neighbors;

    public Territory(String name, String[] neighbors) {
        this.name = name;
        this.neighbors = neighbors;
    }

    public boolean isNeighbor(String n) {
        for (String neighbor : neighbors) {
            if (neighbor.equals(n)) {
                return true;
            }
        }
        return false;
    }
}
