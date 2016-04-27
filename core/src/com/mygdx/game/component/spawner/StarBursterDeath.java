package com.mygdx.game.component.spawner;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.instantiate.EntityBlueprint;

public class StarBursterDeath extends SpawnOnCollision
{
    public StarBursterDeath(final Entity entity, final EntityBlueprint blueprint)
    {
	super(entity, blueprint);
    }

    @Override
    protected void instantiate() {

	float baseRotation = getTransform().getRotation();
	float angleStep = 360f/4f;

	for(int i = 0; i < 4; i++){
	    float nextRotation = baseRotation + angleStep * i;
	    blueprint.instantiate(getEntity().getWorld(), new Transform(new Vector2(getTransform().getPosition()), new Vector2(1,1), nextRotation));
	}
    }

    @Override
    public void update(final float deltaTime) {

    }
}
