package com.mygdx.game.EntityBlueprints;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.EntityBuilder;
import com.mygdx.engine.entity.defaultcomponents.CircleCollider;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;
import com.mygdx.engine.entity.defaultcomponents.SpriteComponent;
import com.mygdx.game.ControllerComponent;

public class PlayerEntity extends EntityBuilder
{
    @Override
    protected void assemble() {

	addComponent(new SpriteComponent(getEntity(), 0, new Vector2(0, 0), new Vector2(1, 1), 0, new Texture("Player.png")));
	addComponent(new RigidBody(getEntity(), 10f));
	addComponent(new ControllerComponent(getEntity()));
	addComponent(new CircleCollider(getEntity(), 30f, (byte)0));
    }
}
