package uk.co.zacgarby.battlesim;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.HdpiMode;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("battlesim");
		config.setWindowedMode(800, 800);
		config.setHdpiMode(HdpiMode.Pixels);
		config.setResizable(false);
		new Lwjgl3Application(new Battlesim(), config);
	}
}
