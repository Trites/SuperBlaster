package com.mygdx.game.entities.component.spawner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.instantiate.EntityBlueprint;
import com.mygdx.engine.util.Util;
import com.mygdx.game.entities.component.util.Timer;

/**
 * Instantiates the given blueprint repeatedly with a random delay in the given interval.
 */
public class TimedSpawner extends Timer
{
    private EntityBlueprint blueprint;
    private float minTime;
    private float maxTime;


    public TimedSpawner(final Entity entity, final EntityBlueprint blueprint, final float minTime, final float maxTime) {
	super(entity, 0.0f);

        this.blueprint = blueprint;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    protected void invoke() {

        instantiate();
        reset(Util.random(minTime, maxTime));
    }

    private void instantiate(){

        Transform transform = new Transform(new Vector2((float)Math.random() * Gdx.graphics.getWidth(), (float)Math.random() * Gdx.graphics.getHeight()));
        blueprint.instantiate(getEntity().getWorld(), transform);
    }
}
