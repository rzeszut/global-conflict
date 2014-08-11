
package edu.globalconflict;

import com.badlogic.gdx.Game;
import edu.globalconflict.screen.GameScreen;
import edu.globalconflict.screen.LoadingGameScreen;
import edu.globalconflict.screen.MainMenuScreen;

public class TheGame extends Game {
    public MainMenuScreen mainMenuScreen;
    public LoadingGameScreen loadingGameScreen;
    public GameScreen gameScreen;

    @Override
    public void create() {
        MainAssets.load();

        mainMenuScreen = new MainMenuScreen(this);
        loadingGameScreen = new LoadingGameScreen(this);
        gameScreen = new GameScreen(this);

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

    public void startGame() {
        setScreen(gameScreen);
    }
}
