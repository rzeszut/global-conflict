package edu.globalconflict.component;

import edu.globalconflict.entity.Component;

/**
 * Component representing territory's state of selection.
 *
 * @author mateusz
 * @since 16.08.14
 */
public final class TerritorySelected implements Component {
    /**
     * Is territory selected.
     */
    public boolean selected = false;
    /**
     * Is this a new event -- a change of selection.
     */
    public boolean isNew = false;

    public void select(boolean select) {
        if (select) {
            selected = !selected;
            isNew = true;
        }
    }
}
