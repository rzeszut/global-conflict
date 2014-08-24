package edu.globalconflict.component.territory;

import edu.globalconflict.entity.EventComponent;

/**
 * Component representing territory's state of selection.
 *
 * @author mateusz
 * @since 16.08.14
 */
public final class TerritorySelected extends EventComponent {
    /**
     * Is territory selected.
     */
    public boolean selected = false;

    public void toggle() {
        selected = !selected;
        isNew = true;
    }

    public void unselect() {
        if (selected) {
            selected = false;
            isNew = true;
        }
    }
}
