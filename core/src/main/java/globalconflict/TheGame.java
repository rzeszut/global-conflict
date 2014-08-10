
package globalconflict;

import com.badlogic.gdx.Game;
import globalconflict.screen.GameScreen;
import globalconflict.screen.MainMenuScreen;

public class TheGame extends Game {
    public MainMenuScreen mainMenuScreen;
    public GameScreen gameScreen;

    @Override
    public void create() {
        Assets.load();

        mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);

        setScreen(mainMenuScreen);
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }

    public void startGame() {
        setScreen(gameScreen);
    }
}
