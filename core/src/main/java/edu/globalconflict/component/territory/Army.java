package edu.globalconflict.component.territory;

import edu.globalconflict.entity.Component;

/**
 * Component representing an army on a given territory.
 *
 * @author mateusz
 * @since 22.08.14
 */
public final class Army implements Component {
    /**
     * Number of troops on the territory. Starting number is always 1.
     */
    public int troops = 1;

    public String getText() {
        return String.valueOf(troops);
    }
}
