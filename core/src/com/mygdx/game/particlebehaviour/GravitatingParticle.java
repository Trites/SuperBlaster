package com.mygdx.game.particlebehaviour;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.particle.ParticleData;

public final class GravitatingParticle
{
    private GravitatingParticle() {}

    public static boolean update(Transform transform, ParticleData particleData, final float deltaTime) {

	particleData.getVelocity().add(transform.getForwardVector().scl(200.0f * deltaTime));

	particleData.updateLifeTime(deltaTime);

	transform.getPosition().mulAdd(particleData.getVelocity(), deltaTime);

 	return particleData.getLifeTime() > 0;
     }
}
