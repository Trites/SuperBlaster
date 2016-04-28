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
import com.mygdx.game.component.DeathParticleEffectOnCollision;
import com.mygdx.game.component.EnableComponentsOnTimer;
import com.mygdx.game.component.KillOnTargetDeath;
import com.mygdx.game.component.NotifyDeath;
import com.mygdx.game.component.SpawnAnimation;
import com.mygdx.game.component.controller.BeamCannon;
import com.mygdx.game.component.controller.FollowController;
import com.mygdx.game.component.controller.MissileController;
import com.mygdx.game.component.controller.PlayerController;
import com.mygdx.game.component.screenlimits.BounceOnBounds;
import com.mygdx.game.component.screenlimits.DeleteOutOfBounds;
import com.mygdx.game.component.spawner.StarBursterDeath;
import com.mygdx.game.component.spawner.TimedSpawner;

/**
 * Util class containing blueprints for all Entities in the demo.
 * Ideally this would be handled by a scripting language or GUI.
 */
public final class EntityBlueprints
{

    public static final float PLAYER_MASS = 150.0f;
    public static final float PLAYER_LINEAR_DAMPENING = 0.008f;
    public static final float PLAYER_RADIUS = 30.0f;
    public static final float PLAYER_SPAWN_DELAY = 0.5f;
    public static final float ENEMY_MASS = 10.0f;
    public static final float ENEMY_LINEAR_DAMPENING = 0.008f;
    public static final float FOLLOWER_RADIUS = 30.0f;
    public static final float FOLLOWER_MAX_VELOCITY = 200.0f;
    public static final float ENEMY_SPAWN_DELAY = 2.0f;
    public static final float STARBURSTER_RADIUS = 30.0f;
    public static final float STARBURSTER_MAX_VELOCITY = 150.0f;
    public static final float STARFRAGMENT_RADIUS = 15.0f;
    public static final float STARFRAGMENT_INVINSIBLE_TIME = 1.0f;
    public static final float PROJECTILE_MASS = 10.0f;
    public static final float PROJECTILE_RADIUS = 7.0f;
    public static final float FOLLOWER_SPAWN_MIN_TIME = 6.0f;
    public static final float FOLLOWER_SPAWN_MAX_TIME = 6.0f;
    public static final float STARBURSTER_SPAWN_MIN_TIME = 5.0f;
    public static final float STARBURSTER_SPAWN_MAX_TIME = 5.0f;

    private EntityBlueprints() {}

    public static Entity instantiatePlayer(World world, Transform transform){

 	Entity entity = new Entity(world, transform);
 	entity.addComponent(new RigidBody(entity, PLAYER_MASS, PLAYER_LINEAR_DAMPENING, 0.0f));
 	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("Player.png")));
 	entity.addComponent(new PlayerController(entity));
 	entity.addComponent(new CircleCollider(entity, PLAYER_RADIUS, (byte)0));
 	//entity.addComponent(new Cannon(entity));
 	entity.addComponent(new BeamCannon(entity));
 	entity.addComponent(new BounceOnBounds(entity));
 	entity.addComponent(new NotifyDeath(entity));
	entity.addComponent(new SpawnAnimation(entity, PLAYER_SPAWN_DELAY));
 	entity.setTag("Player");
	entity.getComponent(SpriteComponent.class).setColor(Color.YELLOW);
 	return entity;
     }

    public static Entity instantiateFollower(final World world, final Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, ENEMY_MASS, ENEMY_LINEAR_DAMPENING, 0.0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("GammaSmasher.png")));
	entity.addComponent(new CircleCollider(entity, FOLLOWER_RADIUS, (byte)1));
	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
	entity.addComponent(new FollowController(entity, "Player", FOLLOWER_MAX_VELOCITY));
	//entity.addComponent(new KillOnCollision(entity, true));
	entity.addComponent(new DeathParticleEffectOnCollision(entity, true));
	entity.addComponent(new SpawnAnimation(entity, ENEMY_SPAWN_DELAY));
	entity.getComponent(SpriteComponent.class).setColor(Color.PURPLE);
	return entity;
    }

    public static Entity instantiateStarBurster(final World world, final Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, ENEMY_MASS, ENEMY_LINEAR_DAMPENING, 0.0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("StarBurster.png")));
	entity.addComponent(new CircleCollider(entity, STARBURSTER_RADIUS, (byte)1));
	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
	entity.addComponent(new FollowController(entity, "Player", STARBURSTER_MAX_VELOCITY));
	entity.addComponent(new SpawnAnimation(entity, ENEMY_SPAWN_DELAY));
	entity.addComponent(new DeathParticleEffectOnCollision(entity, true));
	entity.addComponent(new StarBursterDeath(entity, EntityBlueprints::instantiateStarFragment));
	entity.getComponent(SpriteComponent.class).setColor(Color.CYAN);
	return entity;
    }

    public static Entity instantiateStarFragment(final World world, final Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, ENEMY_MASS, ENEMY_LINEAR_DAMPENING, 0.0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("StarFragment.png")));
	entity.addComponent(new CircleCollider(entity, STARFRAGMENT_RADIUS, (byte)1));
	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
	entity.addComponent(new MissileController(entity, "Player"));
	entity.addComponent(new DeathParticleEffectOnCollision(entity, false));

	entity.addComponent(new EnableComponentsOnTimer(entity, STARFRAGMENT_INVINSIBLE_TIME, new Class[] {DeathParticleEffectOnCollision.class}));
	entity.getComponent(SpriteComponent.class).setColor(Color.FIREBRICK);
	return entity;
    }

    public static Entity instantiateBasicProjectile(World world, Transform transform){

	Entity entity = new Entity(world, transform);
	entity.addComponent(new RigidBody(entity, PROJECTILE_MASS, 0.0f, 0.0f));
	entity.addComponent(new SpriteComponent(entity, 0, new Texture("Plasma.png")));
	entity.addComponent(new CircleCollider(entity, PROJECTILE_RADIUS, (byte)2));
	entity.addComponent(new DeleteOutOfBounds(entity));
	entity.setTag("Projectile");
	return entity;
    }

    public static Entity instantiateFollowerSpawner(World world, Transform transform){

	Entity entity = new Entity(world, new Transform(new Vector2(0,0)));
	entity.addComponent(new TimedSpawner(entity, EntityBlueprints::instantiateFollower, FOLLOWER_SPAWN_MIN_TIME,
					     FOLLOWER_SPAWN_MAX_TIME));
	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
	return entity;
    }

    public static Entity instantiateStarFragmentSpawner(World world, Transform transform){

 	Entity entity = new Entity(world, new Transform(new Vector2(0,0)));
 	entity.addComponent(new TimedSpawner(entity, EntityBlueprints::instantiateStarBurster, STARBURSTER_SPAWN_MIN_TIME,
					     STARBURSTER_SPAWN_MAX_TIME));
 	entity.addComponent(new KillOnTargetDeath(entity, "Player"));
 	return entity;
     }
}
