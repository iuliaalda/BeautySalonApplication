package org.bsa.exceptions;

public class InvalidRole extends Exception {
    public InvalidRole(){
        super(String.format("Incorrect role!"));
    }
}
