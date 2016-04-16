package com.mygdx.engine.particle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.Renderable;

public class Particle implements Renderable
{

    private boolean active;
    private Texture texture;
    private Vector2 origin;

    private Transform transform;
    private ParticleData particleData;

    private ParticleBehaviour behaviour;


    public void create(final Texture texture, final Vector2 position, final Vector2 velocity, final float lifeTime, final ParticleBehaviour behaviour){
	this.create(texture, new Transform(position, new Vector2(1,1), 0f), new ParticleData(velocity, Color.WHITE, lifeTime), behaviour);
    }

    public void create(final Texture texture, final Transform transform, final ParticleData particleData, final ParticleBehaviour behaviour){

	this.texture = texture;
	this.transform = transform;
	this.particleData = particleData;
	this.behaviour = behaviour;

	this.active = true;
	this.origin =  new Vector2(texture.getWidth()/2f, texture.getHeight()/2f);
    }

    public void update(final float deltaTime){

	active = behaviour.update(transform, particleData, deltaTime);
    }

    @Override
    public void render(final SpriteBatch batch) {

	batch.setColor(particleData.getColor());
	batch.draw(texture, transform.getX() - origin.x, transform.getY() - origin.y, origin.x, origin.y, texture.getWidth(), texture.getHeight(),
		   transform.getScaleX(), transform.getScaleY(), transform.getRotation(), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    public boolean isActive() {
	return active;
    }
}
