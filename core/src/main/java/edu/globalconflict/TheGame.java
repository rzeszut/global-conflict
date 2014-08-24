
package edu.globalconflict;

import com.badlogic.gdx.Game;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.screen.MainMenuScreen;
import edu.globalconflict.screen.game.GameScreen;
import edu.globalconflict.screen.loading.LoadingGameScreen;

public final class TheGame extends Game {
    public MainMenuScreen mainMenuScreen;
    public LoadingGameScreen loadingGameScreen;
    public GameScreen gameScreen;

    @Override
    public void create() {
        MainAssets.load();

        mainMenuScreen = new MainMenuScreen(this);
        loadingGameScreen = new LoadingGameScreen(this);
        gameScreen = new GameScreen();

        setScreen(mainMenuScreen);
    }

    @Override
    public void dispose() {
        MainAssets.dispose();
        GameAssets.dispose();
    }

    public void startLoadingGameAssets() {
        setScreen(loadingGameScreen);
    }

    public void startGame(EntityManager entityManager) {
        gameScreen.setEntityManager(entityManager);
        setScreen(gameScreen);
    }
}
