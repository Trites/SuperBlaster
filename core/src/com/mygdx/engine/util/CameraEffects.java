package com.mygdx.engine.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraEffects
{
    private static OrthographicCamera camera;
    private static Vector3 cameraOrigin;

    private static boolean isShaking;
    private static float shakeMagnitude;
    private static float shakeTimer;

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



		Vector3 targetdirection = new Vector3((float)Math.random() - 0.5f, (float)Math.random() - 0.5f, 0f).nor();
  		Vector3 positionDelta = targetdirection.scl((float)Math.random()*shakeMagnitude);
  		camera.position.lerp(new Vector3(cameraOrigin).add(positionDelta), 0.5f);
	    }else{

		shakeTimer = 0f;
		isShaking = false;
		shakeMagnitude = 0f;
	    }
	}

	camera.update();
    }

    public static void CameraShake(float magnitude, float duration){

	if(magnitude >= shakeMagnitude){

	    isShaking = true;
	    shakeMagnitude = magnitude;
	    shakeTimer = duration;
	}
    }
}
