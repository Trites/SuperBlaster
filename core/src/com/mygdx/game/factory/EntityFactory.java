package com.mygdx.game.factory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.game.component.BounceOnBounds;
import com.mygdx.game.component.Cannon;
import com.mygdx.game.component.DeleteOutOfBounds;
import com.mygdx.game.component.DestroyWhenSlow;
import com.mygdx.game.component.FollowController;
import com.mygdx.game.component.PlayerController;
import com.mygdx.game.component.Projectile;
import com.mygdx.game.component.SpawnEnemy;

public class EntityFactory
{
    public static Entity BuildPlayer(World world, Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, 150f, 0.008f, 0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("Player.png")));
	entity.addComponent(new PlayerController(entity));
	entity.addComponent(new CircleCollider(entity, 30f, (byte)0));
	entity.addComponent(new Cannon(entity));
	entity.addComponent(new BounceOnBounds(entity));
	entity.setTag("Player");
	return entity;
    }

    public static Entity BuildFollower(World world, Transform transform){


	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, 10f, 0.008f, 0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("GammaSmasher.png")));
	entity.addComponent(new SpawnEnemy(entity));
	entity.addComponent(new CircleCollider(entity, 30f, (byte)1));
	entity.getComponent(SpriteComponent.class).setColor(Color.PURPLE);
	return entity;
    }

    public static Entity BasicProjectile(World world, Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, 10f, 0f, 0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Texture("Plasma.png")));
	entity.addComponent(new CircleCollider(entity, 7f, (byte)2));
	entity.addComponent(new Projectile(entity));
	entity.addComponent(new DeleteOutOfBounds(entity));
	entity.setTag("Projectile");
	return entity;
    }

    public static Entity ScrapProjectile(World world, Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, 10f, 0.08f, 0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Texture("Plasma.png")));
	entity.addComponent(new CircleCollider(entity, 7f, (byte)0));
	entity.addComponent(new DestroyWhenSlow(entity, 10f));
	return entity;
    }
}
