package com.mygdx.engine.entity.defaultcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

/**
 * ManagedComponent that can draw a Sprite.
 */
public class SpriteComponent extends RenderComponent
{
    private Sprite sprite;

    public SpriteComponent(final Entity entity, final int renderLayer, final Texture texture)
    {
        super(entity, renderLayer);

        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
    }

    @Override
    public void render(final SpriteBatch renderer) {

        sprite.setCenter(getEntity().getTransform().getX(),
                         getEntity().getTransform().getY());

        sprite.setScale(getEntity().getTransform().getScaleX(),
                        getEntity().getTransform().getScaleY());

        sprite.setRotation(getEntity().getTransform().getRotation());
        sprite.setOriginCenter();
        sprite.draw(renderer);
    }

    public void setColor(final Color color) { sprite.setColor(color); }

    public Color getColor() { return sprite.getColor(); }
}
