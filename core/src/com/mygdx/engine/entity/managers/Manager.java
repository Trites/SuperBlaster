package com.mygdx.engine.entity.managers;

import com.mygdx.engine.entity.Entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Manager<T>
{

    private Queue<T> addQueue;
    private Queue<T> removeQueue;

    public Manager(){

        addQueue = new LinkedList<>();
        removeQueue = new LinkedList<>();
    }

    protected abstract void add(T object);
    protected abstract void remove(T object);

    public void queueAdd(T object){

        addQueue.add(object);
    }

    public void queueRemoval(T object){

        removeQueue.add(object);
    }

    public void update(final float deltaTime){

        applyRemoveQueue();
        applyAddQueue();
    }

    private void applyAddQueue(){

        while(!addQueue.isEmpty())
            add(addQueue.remove());
    }

    private void applyRemoveQueue(){

        while(!removeQueue.isEmpty())
            remove(removeQueue.remove());
    }
}
