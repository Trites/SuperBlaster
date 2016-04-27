package com.mygdx.game.factory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Component;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.engine.entity.managers.World;
import com.mygdx.game.component.DeathParticleEffectOnCollision;
import com.mygdx.game.component.EnableComponentsOnTimer;
import com.mygdx.game.component.KillOnCollision;
import com.mygdx.game.component.KillOnTargetDeath;
import com.mygdx.game.component.NotifyDeath;
import com.mygdx.game.component.PlayerSpawnEffect;
import com.mygdx.game.component.SpawnAnimation;
import com.mygdx.game.component.controller.BeamCannon;
import com.mygdx.game.component.controller.FollowController;
import com.mygdx.game.component.controller.MissileController;
import com.mygdx.game.component.controller.PlayerController;
import com.mygdx.game.component.screenlimits.BounceOnBounds;
import com.mygdx.game.component.screenlimits.DeleteOutOfBounds;
import com.mygdx.game.component.spawner.StarBursterDeath;
import com.mygdx.game.component.spawner.TimedSpawner;

public class EntityBlueprints
{
    public static Entity instantiatePlayer(World world, Transform transform){

 	Entity entity = new Entity(world, transform);
 	entity.addComponent(new RigidBody(entity, 150f, 0.008f, 0f));
 	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("Player.png")));
 	entity.addComponent(new PlayerController(entity));
 	entity.addComponent(new CircleCollider(entity, 30f, (byte)0));
 	//entity.addComponent(new Cannon(entity));
 	entity.addComponent(new BeamCannon(entity));
 	entity.addComponent(new BounceOnBounds(entity));
 	entity.addComponent(new NotifyDeath(entity));
	entity.addComponent(new SpawnAnimation(entity, 0.5f));
 	entity.setTag("Player");
	entity.getComponent(SpriteComponent.class).setColor(Color.YELLOW);
 	return entity;
     }

    public static Entity instantiateFollower(final World world, final Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, 10f, 0.008f, 0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("GammaSmasher.png")));
	entity.addComponent(new CircleCollider(entity, 30f, (byte)1));
	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
	entity.addComponent(new FollowController(entity, "Player", 200f));
	//entity.addComponent(new KillOnCollision(entity, true));
	entity.addComponent(new DeathParticleEffectOnCollision(entity, true));
	entity.addComponent(new SpawnAnimation(entity, 2f));
	entity.getComponent(SpriteComponent.class).setColor(Color.PURPLE);
	return entity;
    }

    public static Entity instantiateStarBurster(final World world, final Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, 10f, 0.008f, 0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("StarBurster.png")));
	entity.addComponent(new CircleCollider(entity, 30f, (byte)1));
	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
	entity.addComponent(new FollowController(entity, "Player", 150f));
	entity.addComponent(new SpawnAnimation(entity, 2f));
	entity.addComponent(new DeathParticleEffectOnCollision(entity, true));
	entity.addComponent(new StarBursterDeath(entity, (x,y)->EntityBlueprints.instantiateStarFragment(x,y)));
	entity.getComponent(SpriteComponent.class).setColor(Color.CYAN);
	return entity;
    }

    public static Entity instantiateStarFragment(final World world, final Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, 10f, 0.008f, 0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("StarFragment.png")));
	entity.addComponent(new CircleCollider(entity, 15f, (byte)1));
	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
	entity.addComponent(new MissileController(entity, "Player"));
	entity.addComponent(new DeathParticleEffectOnCollision(entity, false));

	entity.addComponent(new EnableComponentsOnTimer(entity, 1f, new Class[] {DeathParticleEffectOnCollision.class}));
	entity.getComponent(SpriteComponent.class).setColor(Color.FIREBRICK);
	return entity;
    }

    public static Entity instantiateBasicProjectile(World world, Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, 10f, 0f, 0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Texture("Plasma.png")));
	entity.addComponent(new CircleCollider(entity, 7f, (byte)2));
	entity.addComponent(new DeleteOutOfBounds(entity));
	entity.setTag("Projectile");
	return entity;
    }

    public static Entity instantiateFollowerSpawner(World world, Transform transform){

	Entity entity = new Entity(world, new Transform(new Vector2(0,0)));
	entity.addComponent(new TimedSpawner(entity, (x, y)->EntityBlueprints.instantiateFollower(x, y), 6f, 6f));
	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
	return entity;
    }

    public static Entity instantiateStarFragmentSpawner(World world, Transform transform){

 	Entity entity = new Entity(world, new Transform(new Vector2(0,0)));
 	entity.addComponent(new TimedSpawner(entity, (x, y)->EntityBlueprints.instantiateStarBurster(x, y), 5f, 5f));
 	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
 	return entity;
     }
}
