package com.mygdx.game.factory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.game.component.PlayerController;

public class EntityFactory
{
    public static Entity BuildPlayer(World world, Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, 10f, 0.008f, 0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("Player.png")));
	entity.addComponent(new PlayerController(entity));
	entity.addComponent(new CircleCollider(entity, 30f, (byte)0));
	return entity;
    }
}
