package com.mygdx.engine.entity.managers;

import com.mygdx.engine.entity.Destroyable;
import com.mygdx.engine.entity.Startable;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Base for manager classes. Contains add and remove functionallity.
 * @param <T> Type of object this manager will handle.
 */
public abstract class Manager<T extends Startable & Destroyable> implements Startable
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

    /**
     * Adds the component at the start of next update.
     * Used to avoid concurrency and null pointer errors.
     * @param element The component to be added.
     */
    public void queueAdd(T element){

        addQueue.add(element);
    }

    /**
     * Tags the component for removal at the start of the next update.
     * Used to avoid concurrency and null pointer errors.
     * @param element The component to be removed.
     */
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

            remove(removeQueue.remove());
        }
    }

    public void clear(){

        addQueue.clear();
        removeQueue.clear();
    }
}
