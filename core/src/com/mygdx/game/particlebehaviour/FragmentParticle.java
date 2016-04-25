package com.mygdx.game.particlebehaviour;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.particle.ParticleData;

public class FragmentParticle
{
    public static boolean update(Transform transform, ParticleData particleData, final float deltaTime) {

	transform.getPosition().mulAdd(particleData.getVelocity(), deltaTime);



	if(particleData.getVelocity().len() < 10){


	    particleData.updateLifeTime(deltaTime);

	    if(particleData.getLifeTime() <= 0.2f){

		particleData.setAlpha((float)Math.random());
	    }
	}

	particleData.getVelocity().lerp(new Vector2(0,0), 0.08f);
	return particleData.getLifeTime() > 0;
    }
}
