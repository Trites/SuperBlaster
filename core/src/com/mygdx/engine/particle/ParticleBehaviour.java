package com.mygdx.engine.particle;
import com.mygdx.engine.entity.Transform;

public interface ParticleBehaviour
{
    //public void start(final Vector2 spawnPosition);
    public boolean update(Transform transform, ParticleData particleData, final float deltaTime);
}
