package com.mygdx.game.factory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.particle.ParticleData;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.engine.util.Util;
import com.mygdx.game.particlebehaviour.FragmentParticle;

/**
 * Class containing som basic particle effect blueprints.
 */
public final class ParticleFactory
{

    private static final float TWO_PI_DEG = 360.0f;
    private static final int PARTICLE_VEL_VARIATION = 1500;
    private static final int PARTICLE_VEL_BASE = 150;
    private static final float DIRECTIONAL_COLOR_VARIATION = 0.8f;
    private static final float PARTICLE_LIFE_TIME = 0.2f;
    private static final float CIRCULAR_COLOR_VARIATION = 1.0f;
    private static final float CIRCULAR_PARTICLE_LIFE_TIME = 2.0f;

    private ParticleFactory() {}

    public static void directionalDeathParticle(final Vector2 position, final float spawnRadius, final Vector2 initVel, final Vector2 otherPos,
						Vector2 otherVel, float otherMass, float particleMassSpan, float particleMassAdjust,
						final Color color, final int count){


 	float angleStep = TWO_PI_DEG / count;

 	for(int i = 0; i < count; i++){

 	    float angle = angleStep*i;
 	    float dist = ((float)Math.random() * spawnRadius);

 	    Vector2 pos = new Vector2((float)Math.cos(angle)*dist, (float)Math.sin(angle)*dist).add(position);
 	    Vector2 vel = Util.getBounceVelocity(pos, otherPos, initVel, otherVel, (float)Math.random()*particleMassSpan + particleMassAdjust, otherMass);

 	    Transform transform = new Transform(new Vector2(pos), new Vector2(1, 1), 0);
 	    ParticleData data = new ParticleData(vel, Util.shiftColor(color, DIRECTIONAL_COLOR_VARIATION), PARTICLE_LIFE_TIME);
 	    transform.lookAt(new Vector2(position).mulAdd(transform.getForwardVector(), -1));

	    //noinspection MagicNumber
	    transform.setRotation(transform.getRotation() + 45);



 	    ParticleSystem.getInstance().spawn("Plasma.png", transform, data, FragmentParticle::update);
 	}
     }

    public static void circularDeathParticle(final Vector2 position, final Color color, final int count){

	float angleStep = TWO_PI_DEG / count;
	for(int i = 0; i < count; i++){

	    float angle = angleStep*i;
	    float vel = ((float)Math.random() * PARTICLE_VEL_VARIATION) + PARTICLE_VEL_BASE;

	    Transform transform = new Transform(new Vector2(position), new Vector2(1, 1), angle);

	    ParticleData data = new ParticleData(new Vector2(transform.getForwardVector()).scl(vel),
						 Util.shiftColor(color, CIRCULAR_COLOR_VARIATION),
						 CIRCULAR_PARTICLE_LIFE_TIME);

	    transform.lookAt(new Vector2(position).mulAdd(transform.getForwardVector(), -1));

	    //noinspection MagicNumber
	    transform.setRotation(transform.getRotation() + 45);



	    ParticleSystem.getInstance().spawn("Plasma.png", transform, data, FragmentParticle::update);
	}
    }
}
