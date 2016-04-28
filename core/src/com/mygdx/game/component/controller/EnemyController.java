package com.mygdx.game.component.controller;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;

public abstract class EnemyController extends Controller
{
    private String targetTag;
    protected Transform target = null;

    protected EnemyController(final Entity entity, final String targetTag) {
	super(entity);

	this.targetTag = targetTag;
    }

    @Override
    public void start() {
	super.start();
	target = findEntity(targetTag).get(0).getTransform();
    }
}
