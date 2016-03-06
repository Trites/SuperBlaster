package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGame;

/**
 * LibGDX DesktopLauncher
 */
public final class DesktopLauncher {

    	private DesktopLauncher(){}

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    //noinspection ResultOfObjectAllocationIgnored
	    new LwjglApplication(new MainGame(), config);
	}
}
