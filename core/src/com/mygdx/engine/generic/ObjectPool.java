package com.mygdx.engine.generic;

import java.util.Stack;

public abstract class ObjectPool<T>
{
    Stack<T> pool;

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
