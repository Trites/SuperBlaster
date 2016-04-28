package com.mygdx.engine.entity.defaultcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

public class SpriteComponent extends RenderComponent
{

    private Vector2 relativePosition;
    private Vector2 relativeScale;
    private float relativeRotation;

    private Sprite sprite;

    public SpriteComponent(final Entity entity, final int renderLayer, Texture texture){
        this(entity, renderLayer, new Vector2(0,0), new Vector2(1,1), 0, texture);
    }

    public SpriteComponent(final Entity entity, final int renderLayer, final Vector2 relativePosition,
                           final Vector2 relativeScale, final float relativeRotation, final Texture texture)
    {
        super(entity, renderLayer);
        this.relativePosition = relativePosition;
        this.relativeScale = relativeScale;
        this.relativeRotation = relativeRotation;


        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
    }

    @Override
    public void render(final SpriteBatch renderer) {

        sprite.setCenter(getEntity().getTransform().getX() + relativePosition.x,
                         getEntity().getTransform().getY() + relativePosition.y);

        sprite.setScale(getEntity().getTransform().getScaleX() * relativeScale.x,
                        getEntity().getTransform().getScaleY() * relativeScale.y);

        sprite.setRotation(getEntity().getTransform().getRotation() + relativeRotation);
        sprite.setOriginCenter();
        sprite.draw(renderer);
    }

    public Vector2 getRelativePosition() {
        return relativePosition;
    }

    public void setRelativePosition(final Vector2 relativePosition) {
        this.relativePosition = relativePosition;
    }

    public Vector2 getRelativeScale() {
        return relativeScale;
    }

    public void setRelativeScale(final Vector2 relativeScale) {
        this.relativeScale = relativeScale;
    }

    public float getRelativeRotation() {
        return relativeRotation;
    }

    public void setRelativeRotation(final float relativeRotation) {
        this.relativeRotation = relativeRotation;
    }

    public void setColor(final Color color) { sprite.setColor(color); }

    public Color getColor() { return sprite.getColor(); }
}
