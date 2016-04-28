package com.mygdx.engine.particle;
import com.mygdx.engine.entity.Transform;


/**
 * Interface for ParticleBehaviour update signature.
 */
public interface ParticleBehaviour
{
    public boolean update(Transform transform, ParticleData particleData, final float deltaTime);
}
