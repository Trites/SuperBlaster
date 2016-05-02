package com.mygdx.engine.generic;

import java.util.Stack;

/**
 * Class that pools objects until they are needed again, resulting in a theoretical performance gain compared to creating and destroying the same object many times.
 * @param <T> Type of object to be pooled.
 */
public abstract class ObjectPool<T>
{
    private Stack<T> pool;

    protected ObjectPool(){

    	pool = new Stack<>();
    }

    /**
     * Put item back into the pool.
     * @param item The item.
     */
    public void checkIn(T item){

	pool.push(item);
    }

    /**
     * Retrive item from the pool, creating a new one if pool is empty.
     * Calls SpawnNew if it needs to create a new item.
     * @return An item of type T
     */
    public T checkOut(){

	if(!pool.empty()){

	    return pool.pop();
	}else{

	    return spawnNew();
	}
    }

    protected abstract T spawnNew();
}
