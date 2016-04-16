package com.mygdx.engine.particle;

import com.badlogic.gdx.math.Vector2;

public interface ParticleBehaviour
{
    public boolean update(Vector2 position, Vector2 scale, Float rotation, final float deltaTime);
}
