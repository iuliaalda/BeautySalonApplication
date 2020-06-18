package org.bsa.exceptions;

public class EqualHour extends Exception {
    public EqualHour() {
        super(String.format("The chosen date is unavailable!"));
    }
}
