package com.mygdx.game.component.screenlimits;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;

/**
 * Behaviour which deletes its entity if it is outside the screen.
 */
public class DeleteOutOfBounds extends Behaviour
{
    public DeleteOutOfBounds(final Entity entity) {
	super(entity);
    }

    @Override
    public void update(final float deltaTime) {

	if(getTransform().getX() < 0 || getTransform().getX() > Gdx.graphics.getWidth()
		|| getTransform().getY() < 0 || getTransform().getY() > Gdx.graphics.getHeight()){

	    getEntity().destroy();
	}
    }
}
