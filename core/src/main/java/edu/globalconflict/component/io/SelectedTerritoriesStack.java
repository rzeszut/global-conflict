package edu.globalconflict.component.io;

import edu.globalconflict.entity.Component;
import edu.globalconflict.util.Stack;

import java.util.UUID;

/**
 * Stack containing id's of all currently selected territories.
 *
 * @author mateusz
 * @since 17.08.14
 */
public final class SelectedTerritoriesStack implements Component {
    public Stack<UUID> territories = new Stack<>(42);
}
