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

    public void checkIn(T item){

	pool.push(item);
    }

    public T checkOut(){

	if(!pool.empty()){

	    return pool.pop();
	}else{

	    return spawnNew();
	}
    }

    protected abstract T spawnNew();
}
