package com.mygdx.engine.entity.defaultcomponents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.ManagedComponent;
import com.mygdx.engine.entity.managers.ComponentManager;

/**
 * ManagedComponent that can be rendered. Can be sorted after renderLayer.
 */
public abstract class RenderComponent extends ManagedComponent implements Renderable<SpriteBatch>, Comparable<RenderComponent>
{

    private int renderLayer;

    protected RenderComponent(final Entity entity, final int renderLayer) {
	super(entity);
	this.renderLayer = renderLayer;
    }

    @Override
    public void register(final ComponentManager world) {

	world.registerComponent(this);
    }

    @Override
    public void deregister(final ComponentManager world) {

	world.deregisterComponent(this);
    }

    @Override
    public int compareTo(final RenderComponent o) {

	if(o.renderLayer < this.renderLayer)
	    return 1;

	return -1;
    }


}
