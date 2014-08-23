package edu.globalconflict.entity;

/**
 * Abstract class for components representing events
 *
 * @author mateusz
 * @since 23.08.14
 */
public abstract class EventComponent implements Component {
    /**
     * Is this a new event.
     */
    public boolean isNew = false;
}
