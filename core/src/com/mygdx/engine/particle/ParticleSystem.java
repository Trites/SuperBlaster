package com.mygdx.engine.particle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.generic.ObjectPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParticleSystem extends ObjectPool<Particle>
{
    private static ParticleSystem instance = null;

    List<Particle> particles;

    private ParticleSystem(){

	particles = new ArrayList<>();
    }

    public static ParticleSystem GetInstance(){

	if(instance == null){

	    instance = new ParticleSystem();
	}

	return instance;
    }

    public void add(Vector2 position){

	Particle particle = Checkout();
	particle.create(new Texture("Plasma.png"), position);
	particles.add(particle);
    }

    public void update(final float deltaTime){

	Iterator<Particle> iterator = particles.iterator();

	while (iterator.hasNext()){

	    Particle particle = iterator.next();
	    particle.update(deltaTime);

	    if(!particle.isActive()){

		iterator.remove();
		Checkin(particle);
	    }
	}
    }

    public void render(final SpriteBatch batch){

	for(final Particle particle : particles)
	    particle.render(batch);
    }

    @Override
    protected Particle spawnNew() {

	return new Particle();
    }
}
