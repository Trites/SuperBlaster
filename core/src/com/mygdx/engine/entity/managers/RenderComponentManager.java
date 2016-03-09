package com.mygdx.engine.entity.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.entity.defaultcomponents.RenderComponent;
import com.mygdx.engine.entity.defaultcomponents.Renderable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RenderComponentManager implements Renderable
{
    private List<RenderComponent> renderables; //Most of the time will be spent iterating, therefor we accept O(n) insertion time.

    public RenderComponentManager() {

	renderables = new ArrayList<RenderComponent>();
    }

    public void add(RenderComponent renderable){

	int index = Collections.binarySearch(renderables, renderable);

	if(index > 0){

	    renderables.add(index, renderable);
	}else{

	    renderables.add(renderable);
	}
    }

    public void remove(RenderComponent renderable){

	renderables.remove(renderable);
    }

    @Override
    public void render(final SpriteBatch batch) {

	for(Renderable renderable : renderables){

	    renderable.render(batch);
	}
    }
}
