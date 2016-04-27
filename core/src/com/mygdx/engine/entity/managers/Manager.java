package com.mygdx.engine.entity.managers;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Manager<T extends Destroyable & Startable> implements Startable
{

    private Queue<T> addQueue;
    private Queue<T> removeQueue;

    public Manager(){

        addQueue = new LinkedList<>();
        removeQueue = new LinkedList<>();
    }

    @Override
    public void start() {

        applyRemoveQueue();
        applyAddQueue();
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

        while(!addQueue.isEmpty()){
            add(addQueue.remove());
        }
    }

    private void applyRemoveQueue(){

        while(!removeQueue.isEmpty()){

            removeQueue.peek().destroyImmediate();
            remove(removeQueue.remove());
        }
    }

    public void clear(){

        addQueue.clear();
        removeQueue.clear();
    }
}
