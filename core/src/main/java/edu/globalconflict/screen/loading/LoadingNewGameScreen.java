package edu.globalconflict.screen.loading;

import edu.globalconflict.TheGame;
import edu.globalconflict.screen.loading.action.GameCreateAction;
import edu.globalconflict.screen.loading.action.NewGameAction;

import java.util.List;

/**
 * @author mateusz
 * @since 29.08.14
 */
public final class LoadingNewGameScreen extends LoadingGameScreen {
    private List<String> playerNames;

    public LoadingNewGameScreen(TheGame game) {
        super(game);
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    @Override
    protected GameCreateAction getGameCreateAction() {
        return new NewGameAction(playerNames);
    }
}
