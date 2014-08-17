package edu.globalconflict.component.game;

import edu.globalconflict.entity.Component;
import edu.globalconflict.util.Stack;

import java.util.UUID;

/**
 * @author mateusz
 * @since 17.08.14
 */
public final class SelectedTerritoriesStack implements Component {
    public Stack<UUID> territories = new Stack<>(42);
}
