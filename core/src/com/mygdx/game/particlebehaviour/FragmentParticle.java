package com.mygdx.game.particlebehaviour;

import com.badlogic.gdx.Gdx;
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

	if(transform.getX() < 0){

	    transform.getPosition().x = 0;
	    particleData.getVelocity().x = -particleData.getVelocity().x;
	}else if(transform.getX() > Gdx.graphics.getWidth()){

	    transform.getPosition().x = Gdx.graphics.getWidth();
	    particleData.getVelocity().x = -particleData.getVelocity().x;
	}

	if(transform.getY() < 0){

	    transform.getPosition().y = 0;
	    particleData.getVelocity().y = -particleData.getVelocity().y;
	}else if(transform.getY() > Gdx.graphics.getHeight()){

	    transform.getPosition().y = Gdx.graphics.getHeight();
	    particleData.getVelocity().y = -particleData.getVelocity().y;
	}

	return particleData.getLifeTime() > 0;
    }
}
