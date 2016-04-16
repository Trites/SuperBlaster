package com.mygdx.engine.particle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.defaultcomponents.Renderable;

public class Particle implements Renderable
{

    private boolean active;
    private Texture texture;
    private Vector2 position;
    private Vector2 origin;
    private Vector2 scale;
    private Float rotation;

    private ParticleBehaviour behaviour;

    public void create(final Texture texture, final Vector2 position, final ParticleBehaviour behaviour){
	this.create(texture, position, new Vector2(1, 1), 0f, behaviour);
    }

    public void create(final Texture texture, final Vector2 position, final Vector2 scale, final float rotation, final ParticleBehaviour behaviour){

	this.texture = texture;
	this.position = position;
	this.scale = scale;
	this.rotation = rotation;
	this.behaviour = behaviour;

	this.active = true;
	this.origin =  new Vector2(texture.getWidth()/2f, texture.getHeight()/2f);
    }

    public void update(final float deltaTime){

	behaviour.update(position, scale, rotation, deltaTime);
    }

    @Override
    public void render(final SpriteBatch batch) {

	batch.draw(texture, position.x - origin.x, position.y - origin.y, origin.x, origin.y, texture.getWidth(), texture.getHeight(),
		   scale.x, scale.y, rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    public boolean isActive() {
	return active;
    }
}
