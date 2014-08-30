package edu.globalconflict.screen.loading;

import edu.globalconflict.TheGame;
import edu.globalconflict.screen.loading.action.GameCreateAction;
import edu.globalconflict.screen.loading.action.LoadGameAction;

/**
 * @author mateusz
 * @since 29.08.14
 */
public final class LoadingSavedGameScreen extends LoadingGameScreen {
    public LoadingSavedGameScreen(TheGame game) {
        super(game);
    }

    @Override
    protected GameCreateAction getGameCreateAction() {
        return new LoadGameAction();
    }
}
