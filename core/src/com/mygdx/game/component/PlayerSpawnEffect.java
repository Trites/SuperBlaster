package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.particle.ParticleSystem;
import com.mygdx.game.factory.ParticleFactory;

public class PlayerSpawnEffect extends Behaviour
{
    public PlayerSpawnEffect(final Entity entity) {
	super(entity);
    }

    @Override
    public void start() {
	super.start();

	ParticleFactory.CircularSpawnParticle(getTransform().getPosition(), Color.ORANGE, 500);

	destroy();
    }

    @Override
    public void update(final float deltaTime) {

    }
}
