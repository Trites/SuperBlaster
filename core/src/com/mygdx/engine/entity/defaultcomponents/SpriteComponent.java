package com.mygdx.engine.entity.defaultcomponents;

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

    public SpriteComponent(final Entity entity, final int renderLayer, final Vector2 relativePosition,
                           final Vector2 relativeScale, final float relativeRotation, final Sprite sprite)
    {
        super(entity, renderLayer);
        this.relativePosition = relativePosition;
        this.relativeScale = relativeScale;
        this.relativeRotation = relativeRotation;
        this.sprite = sprite;
    }

    @Override
    public void update(final float deltaTime) {

        sprite.setPosition(getEntity().getTransform().getPositionX() + relativePosition.x,
                           getEntity().getTransform().getPositionY() + relativePosition.y);
        sprite.setScale(getEntity().getTransform().getScaleX() + relativeScale.x,
                        getEntity().getTransform().getScaleY() + relativeScale.y);
        sprite.setRotation(getEntity().getTransform().getRotation() + relativeRotation);
    }

    @Override
    public void render(final SpriteBatch batch) {

        sprite.draw(batch);
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
}
