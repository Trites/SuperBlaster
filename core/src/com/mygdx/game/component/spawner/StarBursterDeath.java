package com.mygdx.game.component.spawner;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.instantiate.EntityBlueprint;

/**
 * Defines collision behaviour of a StarBurster.
 * It spawns four StarFragments.
 */
public class StarBursterDeath extends SpawnOnCollision
{
    public StarBursterDeath(final Entity entity, final EntityBlueprint blueprint)
    {
	super(entity, blueprint);
    }

    @Override
    protected void instantiate() {

	float baseRotation = getTransform().getRotation();
	//noinspection MagicNumber,MagicNumber
	float angleStep = 360.0f / 4.0f;

	for(int i = 0; i < 4; i++){
	    float nextRotation = baseRotation + angleStep * i;
	    blueprint.instantiate(getEntity().getWorld(), new Transform(new Vector2(getTransform().getPosition()), new Vector2(1,1), nextRotation));
	}
    }

    @Override
    public void update(final float deltaTime) {

    }
}
