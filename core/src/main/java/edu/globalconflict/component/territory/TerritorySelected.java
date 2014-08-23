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

    public void select(boolean select) {
        if (select) {
            selected = !selected;
            isNew = true;
        }
    }
}
