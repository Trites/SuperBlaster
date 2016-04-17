package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.particle.ParticleData;

public class FragmentParticle
{
    public static boolean update(Transform transform, ParticleData particleData, final float deltaTime) {

	transform.getPosition().mulAdd(particleData.getVelocity(), deltaTime);

	if(particleData.getVelocity().len() > 10){

	    //particleData.setColor(new Color(particleData.getVelocity().len()/100f, 0f, 1f - particleData.getVelocity().len()/100f, 1f));

	}else{

	    particleData.updateLifeTime(deltaTime);
	    particleData.setAlpha((float)Math.random());
	}


	particleData.getVelocity().lerp(new Vector2(0,0), 0.08f);
	return particleData.getLifeTime() > 0;
    }
}
