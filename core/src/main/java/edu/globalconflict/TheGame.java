
package edu.globalconflict;

import com.badlogic.gdx.Game;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.screen.MainMenuScreen;
import edu.globalconflict.screen.PauseScreen;
import edu.globalconflict.screen.SelectPlayersScreen;
import edu.globalconflict.screen.game.GameScreen;
import edu.globalconflict.screen.loading.LoadingNewGameScreen;
import edu.globalconflict.screen.loading.LoadingSavedGameScreen;

import java.util.List;

public final class TheGame extends Game {
    private MainMenuScreen mainMenuScreen;
    private SelectPlayersScreen selectPlayersScreen;
    private LoadingNewGameScreen loadingNewGameScreen;
    private LoadingSavedGameScreen loadingSavedGameScreen;
    private GameScreen gameScreen;
    private PauseScreen pauseScreen;

    @Override
    public void create() {
        MainAssets.load();

        mainMenuScreen = new MainMenuScreen(this);
        selectPlayersScreen = new SelectPlayersScreen(this);
        loadingNewGameScreen = new LoadingNewGameScreen(this);
        loadingSavedGameScreen = new LoadingSavedGameScreen(this);
        gameScreen = new GameScreen(this);
        pauseScreen = new PauseScreen(this);

        setScreen(mainMenuScreen);
    }

    @Override
    public void dispose() {
        MainAssets.dispose();
        GameAssets.dispose();
    }

    public void goToMainMenu() {
        setScreen(mainMenuScreen);
    }

    public void goToPlayerSelection() {
        setScreen(selectPlayersScreen);
    }

    public void goToLoadingNewGame(List<String> playerNames) {
        loadingNewGameScreen.setPlayerNames(playerNames);
        setScreen(loadingNewGameScreen);
    }

    public void goToLoadingSavedGame() {
        setScreen(loadingSavedGameScreen);
    }

    public void startGame(EntityManager entityManager) {
        gameScreen.initialize(entityManager);
        setScreen(gameScreen);
    }

    public void resumeGame() {
        setScreen(gameScreen);
    }

    public void pauseGame(EntityManager entityManager) {
        pauseScreen.setEntityManager(entityManager);
        setScreen(pauseScreen);
    }
}
