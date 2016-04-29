package com.mygdx.engine.exception;

/**
 * Thrown when an entity does not have all requiered components when started.
 */
public class MissingDependencyException extends Exception
{
    public MissingDependencyException(final String message) {
	super(message);
    }
}
