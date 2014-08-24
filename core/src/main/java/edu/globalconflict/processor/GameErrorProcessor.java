package edu.globalconflict.processor;

import edu.globalconflict.component.io.GameError;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.EventProcessor;
import edu.globalconflict.screen.game.ErrorDialog;

import java.util.UUID;

/**
 * @author mateusz
 * @since 24.08.14
 */
public final class GameErrorProcessor extends EventProcessor<GameError> {
    private final ErrorDialog dialog;

    public GameErrorProcessor(ErrorDialog dialog) {
        super(GameError.class);
        this.dialog = dialog;
    }

    @Override
    protected void processEvent(EntityManager entityManager, float delta, UUID gameEntity, GameError event) {
        dialog.show(event.errorMessage);
    }
}
