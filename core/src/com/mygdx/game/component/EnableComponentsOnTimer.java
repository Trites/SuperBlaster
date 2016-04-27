package com.mygdx.game.component;

import com.mygdx.engine.entity.Component;
import com.mygdx.engine.entity.Entity;

public class EnableComponentsOnTimer extends Timer
{
    Class<? extends Component>[] components;

    public EnableComponentsOnTimer(final Entity entity, final float time, final Class<? extends Component>[] components) {
	super(entity, time);
	this.components = components;
    }

    @Override
    protected void invoke() {

	for(int i = 0; i < components.length; i++)
	    getComponent(components[i]).setActive(true);
    }
}
