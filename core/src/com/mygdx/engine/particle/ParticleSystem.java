package com.mygdx.engine.particle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.generic.ObjectPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Singleton system that manages particles.
 * Makes use of an ObjectPool to reuse objects.
 */
public final class ParticleSystem extends ObjectPool<Particle>
{
    private static ParticleSystem instance = null;

    private List<Particle> particles;
    private Map<String, Texture> textureMap;


    private ParticleSystem(){

	particles = new ArrayList<>();
	textureMap = new HashMap<>();
    }

    /**
     * Creates a texture from the given file. The texture can then be loaded by supplying the same string again.
     * @param texture
     */
    public void addTexture(final String texture){

	textureMap.put(texture, new Texture(texture));
    }

    /**
     * Gets reference to the particle system singleton.
     * @return The particle system.
     */
    public static ParticleSystem getInstance(){

	if(instance == null){

	    //Singleton pattern is intended
	    instance = new ParticleSystem();
	}

	return instance;
    }

    /**
     * Spawns a new particle.
     * @param texture The texture to be loaded from the texture map.
     * @param transform The initial transform of the particle.
     * @param data The initial particle data.
     * @param behaviour The update behaviour of the particle.
     */
    public void spawn(String texture, Transform transform, ParticleData data, ParticleBehaviour behaviour){

	Particle particle = checkOut();
	particle.create(textureMap.get(texture), transform, data, behaviour);
	particles.add(particle);
    }

    public void update(final float deltaTime){

	Iterator<Particle> iterator = particles.iterator();

	while (iterator.hasNext()){

	    Particle particle = iterator.next();
	    particle.update(deltaTime);

	    if(!particle.isActive()){

		iterator.remove();
		checkIn(particle);
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
