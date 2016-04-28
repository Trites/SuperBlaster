package com.mygdx.game.component;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.events.EventListener;
import com.mygdx.game.factory.ParticleFactory;

public class KillOnTargetDeath extends Behaviour
{

    private String targetTag;
    private NotifyDeath target = null;
    private EventListener<Transform> targetDeathHandler;

    public KillOnTargetDeath(final Entity entity, final String targetTag) {
	super(entity);
	//getEntity().requireComponent(SpriteComponent.class);
	this.targetTag = targetTag;
	targetDeathHandler = this::onTargetDeath;
    }

    @Override
    public void start() {
	super.start();

	Entity targetEntity = getEntity().findEntity(targetTag).get(0);

	if(targetEntity.hasComponent(NotifyDeath.class)){
	    target = targetEntity.getComponent(NotifyDeath.class);
	    target.deathEvent.subscribe(targetDeathHandler);
	}else{

	    System.out.println("NO TARGET!");
	}

    }

    @Override
    public void update(final float deltaTime) {

    }



    private void onTargetDeath(final Transform targetTransform){

	if(getEntity().hasComponent(SpriteComponent.class)){

	    Vector2 diffVector = new Vector2(getTransform().getPosition()).sub(targetTransform.getPosition());
	    float distance = new Vector2(diffVector).len();
	    Vector2 direction = new Vector2(diffVector).nor();

	    ParticleFactory.directionalDeathParticle(getTransform().getPosition(), 30.0f, new Vector2(0, 0), targetTransform.getPosition(),
						     direction.scl(9000.0f / ((float)Math.sqrt(distance / 10.0f) + 1)), 200.0f, 400.0f,
						     5.0f, getComponent(SpriteComponent.class).getColor(), 100);
	}


	getEntity().destroy();
    }

    @Override public void destroyImmediate() {
	super.destroyImmediate();

	if(target != null){

	    target.deathEvent.unsubscribe(targetDeathHandler);
	}
    }
}
