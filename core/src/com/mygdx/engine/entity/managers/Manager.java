package com.mygdx.engine.entity.managers;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Manager<T extends Destroyable & Startable> implements Startable
{

    private Queue<T> addQueue;
    private Queue<T> removeQueue;

    protected Manager(){

        addQueue = new LinkedList<>();
        removeQueue = new LinkedList<>();
    }

    @Override
    public void start() {

        applyRemoveQueue();
        applyAddQueue();
    }

    protected abstract void add(T element);
    protected abstract void remove(T element);

    public void queueAdd(T element){

        addQueue.add(element);
    }

    public void queueRemoval(T element){

        removeQueue.add(element);
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
