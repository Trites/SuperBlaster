package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.particle.ParticleData;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.engine.util.Util;
import com.mygdx.game.FragmentParticle;
import com.mygdx.game.factory.EntityFactory;

public class Projectile extends Behaviour
{

    RigidBody body;

    public Projectile(final Entity entity) {

	super(entity);
	entity.collisionEvent.subscribe((x)->collisionEvent(x));
    }

    @Override
    public void start() {
	super.start();
	body = getComponent(RigidBody.class);
    }

    private void collisionEvent(final CollisionComponent other){

    }

    @Override
    public void update(final float deltaTime) {

    }
}
