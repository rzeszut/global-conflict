
package edu.globalconflict;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) Constants.SCREEN_WIDTH;
        config.height = (int) Constants.SCREEN_HEIGHT;
        config.title = "Global Conflict";
        config.resizable = false;

        new LwjglApplication(new TheGame(), config);
    }
}
