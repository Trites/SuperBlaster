package com.mygdx.engine.entity.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.entity.defaultcomponents.RenderComponent;
import com.mygdx.engine.entity.defaultcomponents.Renderable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manager that handles RenderComponent. Supports render based rendering.
 */
public class RenderManager extends Manager<RenderComponent> implements Renderable<SpriteBatch>
{
    private List<RenderComponent> renderables; //Most of the time will be spent iterating, therefor we accept O(n) insertion time.

    public RenderManager() {

	renderables = new ArrayList<>();
    }

    @Override
    public void add(final RenderComponent element){

	int index = Collections.binarySearch(renderables, element);

	if(index > 0){

	    renderables.add(index, element);
	}else{

	    renderables.add(element);
	}
    }

    @Override
    public void remove(final RenderComponent element){

	renderables.remove(element);
    }

    @Override
    public void render(final SpriteBatch renderer) {

	for(final Renderable<SpriteBatch> renderable : renderables){

	    renderable.render(renderer);
	}
    }

    @Override public void clear() {
	super.clear();
    	renderables.clear();
    }
}
