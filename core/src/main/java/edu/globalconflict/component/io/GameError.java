package edu.globalconflict.component.io;

import edu.globalconflict.entity.EventComponent;

/**
 * Component used for displaying errors.
 *
 * @author mateusz
 * @since 24.08.14
 */
public final class GameError extends EventComponent {
    /**
     * Error message to display.
     */
    public String errorMessage;

    public void set(String message) {
        this.errorMessage = message;
        this.isNew = true;
    }
}
