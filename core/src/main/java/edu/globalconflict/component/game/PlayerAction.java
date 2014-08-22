package edu.globalconflict.component.game;

import edu.globalconflict.entity.Component;

/**
 * Action selected by current player.
 *
 * @author mateusz
 * @since 22.08.14
 */
public final class PlayerAction implements Component {
    /**
     * Selected action.
     */
    public Action action;
    /**
     * Is this a new event.
     */
    public boolean isNew = false;

    public void set(Action action) {
        this.action = action;
        this.isNew = true;
    }

    /**
     * Player action type.
     */
    public interface Action {
    }

    /**
     * Player attacks last selected territory with his second last one.
     * Carries no additional information.
     */
    public static final Action ATTACK = new Action() {
        @Override
        public String toString() {
            return "ATTACK";
        }
    };

    /**
     * Player transfers troops from/to territory to/fom buffer.
     * Contains additional value representing the change in troops.
     */
    public static final TransferAction TRANSFER = new TransferAction();
    public static final class TransferAction implements Action {
        /**
         * Number of transferred troops:
         * + From territory to buffer --> negative value
         * + From buffer to territory --> positive value
         */
        public int transferredTroops;

        @Override
        public String toString() {
            return "TRANSFER";
        }
    }

    /**
     * Player ends his turn.
     * Carries no additional information.
     */
    public static final Action END_TURN = new Action() {
        @Override
        public String toString() {
            return "END TURN";
        }
    };
}
