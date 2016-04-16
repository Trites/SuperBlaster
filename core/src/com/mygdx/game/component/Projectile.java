package com.mygdx.game.component;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.game.factory.EntityFactory;

public class Projectile extends Behaviour
{

    RigidBody body;

    public Projectile(final Entity entity) {
	super(entity);
    }

    @Override
    public void start() {
	super.start();
	body = getComponent(RigidBody.class);
    }

    @Override
    public void update(final float deltaTime) {

	if(body.getVelocity().len() < 10){

	    for(int i = 0; i < 100; i++){

		float angle = (float)Math.random() * 360;
		float vel = ((float)Math.random() * 900) + 300;
		RigidBody scrapBody = EntityFactory.ScrapProjectile(getEntity().getWorld(), new Transform(new Vector2(getTransform().getPosition()), new Vector2(1, 1), angle)).getComponent(RigidBody.class);
	    	scrapBody.addVelocity(new Vector2(0,0).mulAdd(scrapBody.getTransform().getForwardVector(), vel));
	    }

	    getEntity().destroy();
	}
    }
}
