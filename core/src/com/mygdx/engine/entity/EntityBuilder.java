package com.mygdx.engine.entity;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.managers.EntityManager;

public abstract class EntityBuilder
{
    private Entity entity;
    private EntityManager context;


    public Entity build(final EntityManager context, final Transform transform){

	this.context = context;
	//entity = new Entity(transform);
	assemble();
	return entity;
    }

    protected abstract void assemble();

    protected <T extends Behaviour> void addComponent(T component){

	entity.addComponent(component);
    }

//TODO: wat
    protected <T extends ManagedComponent> void addComponent(T component){

	entity.addComponent(component);
    }

    protected final Entity getEntity(){ return entity; }
}
