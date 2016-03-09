package com.mygdx.engine.entity.defaultcomponents;

import com.mygdx.engine.entity.Component;
import com.mygdx.engine.entity.Entity;

public abstract class RenderComponent extends Component implements Renderable, Comparable<RenderComponent>
{

    private int renderLayer;

    protected RenderComponent(final Entity entity, final int renderLayer) {
	super(entity);
	this.renderLayer = renderLayer;
    }

    @Override
    public int compareTo(final RenderComponent o) {

	if(o.renderLayer < this.renderLayer)
	    return 1;

	return -1;
    }

    @Override
    public boolean equals(final Object obj) {

	if(obj.getClass() != this.getClass())
	    return false;

	RenderComponent other = (RenderComponent)obj;

	return other.renderLayer == this.renderLayer;
    }
}
