package com.mygdx.game.component.spawner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.instantiate.EntityBlueprint;

public class TimedSpawner extends Behaviour
{
    private EntityBlueprint blueprint;
    private float minTime;
    private float maxTime;

    private float timer;

    public TimedSpawner(final Entity entity, final EntityBlueprint blueprint, final float minTime, final float maxTime) {
	super(entity);

        this.blueprint = blueprint;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public void update(final float deltaTime) {

        timer -= deltaTime;

        if(timer <= 0){

            instantiate();
            timer = (float) Math.random()*(maxTime-minTime) + minTime;
        }
    }

    private void instantiate(){

        Transform transform = new Transform(new Vector2((float)Math.random() * Gdx.graphics.getWidth(), (float)Math.random() * Gdx.graphics.getHeight()));
        blueprint.instantiate(getEntity().getWorld(), transform);
    }
}
