package com.mygdx.game.entities.component.spawner;

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
    private static final int FRAGMENT_COUNT = 4;
    private static final float ANGLE_STEP = 360.0f / FRAGMENT_COUNT;

    public StarBursterDeath(final Entity entity, final EntityBlueprint blueprint)
    {
	super(entity, blueprint);
    }

    @Override
    protected void instantiate() {

	float baseRotation = getTransform().getRotation();

	for(int i = 0; i < FRAGMENT_COUNT; i++){
	    float nextRotation = baseRotation + ANGLE_STEP * i;
	    blueprint.instantiate(getEntity().getWorld(), new Transform(new Vector2(getTransform().getPosition()), new Vector2(1,1), nextRotation));
	}
    }

    @Override
    public void update(final float deltaTime) {

    }
}
