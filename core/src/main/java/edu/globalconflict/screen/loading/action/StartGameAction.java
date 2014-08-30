package edu.globalconflict.screen.loading.action;

import edu.globalconflict.TheGame;

/**
 * @author mateusz
 * @since 29.08.14
 */
public final class StartGameAction implements Runnable {
    private final TheGame game;
    private final GameCreateAction gameCreateAction;

    public StartGameAction(TheGame game, GameCreateAction gameCreateAction) {
        this.game = game;
        this.gameCreateAction = gameCreateAction;
    }

    @Override
    public void run() {
        game.startGame(gameCreateAction.getEntityManager());
    }
}
