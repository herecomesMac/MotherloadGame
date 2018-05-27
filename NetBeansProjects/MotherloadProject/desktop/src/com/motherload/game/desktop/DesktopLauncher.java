package com.motherload.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.motherload.game.Motherload;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Motherload";
                config.useGL30 = true;
                config.height = 600;
                config.width = 800;
                new LwjglApplication(new Motherload(), config);
	}
}
