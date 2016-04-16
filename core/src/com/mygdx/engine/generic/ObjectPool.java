package com.mygdx.engine.generic;

import java.util.Stack;

public abstract class ObjectPool<T>
{
    Stack<T> pool;

    public ObjectPool(){

    	pool = new Stack<>();
    }

    public void Checkin(T item){

	pool.push(item);
    }

    public T Checkout(){

	if(!pool.empty()){

	    return pool.pop();
	}else{

	    return spawnNew();
	}
    }

    protected abstract T spawnNew();
}
