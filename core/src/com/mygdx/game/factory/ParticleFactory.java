package com.mygdx.game.factory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.particle.ParticleData;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.engine.util.Util;
import com.mygdx.game.FragmentParticle;

public class ParticleFactory
{

    public static void DirectionalDeathParticle(final Vector2 position, final Vector2 initVel, final Vector2 otherPos,
 						Vector2 otherVel, float otherMass, float particleMassSpan, float particleMassAdjust,
 						final Color color, final int count){


 	float angleStep = 360f/count;

 	for(int i = 0; i < count; i++){

 	    float angle = angleStep*i;
 	    float dist = ((float)Math.random() * 32);

 	    Vector2 pos = new Vector2((float)Math.cos(angle)*dist, (float)Math.sin(angle)*dist).add(position);
 	    Vector2 vel = Util.getBounceVelocity(pos, otherPos, initVel, otherVel, (float)Math.random()*particleMassSpan + particleMassAdjust, otherMass);

 	    Transform transform = new Transform(new Vector2(pos), new Vector2(1, 1), 0);
 	    ParticleData data = new ParticleData(vel, Util.shiftColor(color, 0.8f), 0.2f);
 	    transform.lookAt(new Vector2(position).mulAdd(transform.getForwardVector(), -1));
 	    transform.setRotation(transform.getRotation() + 45);



 	    ParticleSystem.GetInstance().spawn("Plasma.png", transform, data, (x, y, z)-> FragmentParticle.update(x, y, z));
 	}
     }

    public static void CircularDeathParticle(final Vector2 position, final Color color, final int count){

	float angleStep = 360f/count;
	for(int i = 0; i < count; i++){

	    float angle = angleStep*i;
	    float vel = ((float)Math.random() * 600) + 150;

	    Transform transform = new Transform(new Vector2(position), new Vector2(1, 1), angle);

	    ParticleData data = new ParticleData(new Vector2(0, 0).mulAdd(transform.getForwardVector(), vel), Util.shiftColor(color, 0.1f), 0.2f);
	    transform.lookAt(new Vector2(position).mulAdd(transform.getForwardVector(), -1));
	    transform.setRotation(transform.getRotation() + 45);



	    ParticleSystem.GetInstance().spawn("Plasma.png", transform, data, (x, y, z)-> FragmentParticle.update(x, y, z));
	}
    }
}
