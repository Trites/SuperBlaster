package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGame;

/**
 * LibGDX DesktopLauncher
 */
public final class DesktopLauncher {

    /**
     * Width of screen in pixels.
     */
    public static final int WIDTH = 1280;
    /**
     * Height of screen in pixels.
     */
    public static final int HEIGHT = 720;

    private DesktopLauncher(){}

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.width = WIDTH;
	    config.height = HEIGHT;

	    //noinspection ResultOfObjectAllocationIgnored
	    new LwjglApplication(new MainGame(), config);
	}
}
