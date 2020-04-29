package org.bsa.exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super(String.format("Empty Field, try again!"));
    }
}