package com.mygdx.engine.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public final class CameraEffects
{
    private static OrthographicCamera camera = null;
    private static Vector3 cameraOrigin = null;

    private static boolean isShaking = false;
    private static float shakeMagnitude = 0.0F;
    private static float shakeTimer = 0.0F;

    private CameraEffects() {}

    public static void setCamera(OrthographicCamera newCamera){

	camera = newCamera;
	cameraOrigin = new Vector3(camera.position);
    }

    public static void update(final float deltaTime) {


	if(!isShaking){

	    camera.position.set(cameraOrigin);
	}else{

	    shakeTimer -= deltaTime;

	    if(shakeTimer > 0){



		Vector3 targetdirection = new Vector3((float)Math.random() - 0.5f, (float)Math.random() - 0.5f, 0.0f).nor();
  		Vector3 positionDelta = targetdirection.scl((float)Math.random()*shakeMagnitude);
  		camera.position.lerp(new Vector3(cameraOrigin).add(positionDelta), 0.5f);
	    }else{

		shakeTimer = 0.0f;
		isShaking = false;
		shakeMagnitude = 0.0f;
	    }
	}

	camera.update();
    }

    public static void cameraShake(float magnitude, float duration){

	if(magnitude >= shakeMagnitude){

	    isShaking = true;
	    shakeMagnitude = magnitude;
	    shakeTimer = duration;
	}
    }
}
