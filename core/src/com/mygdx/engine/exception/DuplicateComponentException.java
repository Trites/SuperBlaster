package com.mygdx.engine.exception;

/**
 * Invoked if two components of the same type is added to an entity
 */
public class DuplicateComponentException extends Exception
{
    public DuplicateComponentException(final String message) {
	super(message);
    }
}
