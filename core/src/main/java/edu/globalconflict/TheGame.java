
package edu.globalconflict;

import com.badlogic.gdx.Game;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.screen.MainMenuScreen;
import edu.globalconflict.screen.SelectPlayersScreen;
import edu.globalconflict.screen.game.GameScreen;
import edu.globalconflict.screen.loading.LoadingGameScreen;

import java.util.List;

public final class TheGame extends Game {
    private MainMenuScreen mainMenuScreen;
    private SelectPlayersScreen selectPlayersScreen;
    private LoadingGameScreen loadingGameScreen;
    private GameScreen gameScreen;

    @Override
    public void create() {
        MainAssets.load();

        mainMenuScreen = new MainMenuScreen(this);
        selectPlayersScreen = new SelectPlayersScreen(this);
        loadingGameScreen = new LoadingGameScreen(this);
        gameScreen = new GameScreen();

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

    public void goToLoadingScreen(List<String> playerNames) {
        loadingGameScreen.setPlayerNames(playerNames);
        setScreen(loadingGameScreen);
    }

    public void startGame(EntityManager entityManager) {
        gameScreen.setEntityManager(entityManager);
        setScreen(gameScreen);
    }

    // TODO: pause game screen
    // resumeGame() method -- move logic from GameScreen#show() to some method initialize(), called only fro mstartGame()
}
