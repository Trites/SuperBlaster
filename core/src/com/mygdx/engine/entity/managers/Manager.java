package com.mygdx.engine.entity.managers;

import com.mygdx.engine.entity.Entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Manager<T>
{
    private Queue<T> removeQueue;

    public Manager(){

        removeQueue = new LinkedList<>();
    }

    public abstract void add(T object);
    public abstract void add(List<T> objects);
    protected abstract void remove(T object);

    public void queueRemoval(T object){

        removeQueue.add(object);
    }

    public void update(final float deltaTime){

        applyRemoveQueue();
    }

    private void applyRemoveQueue(){

        while(!removeQueue.isEmpty())
            remove(removeQueue.remove());
    }
}
