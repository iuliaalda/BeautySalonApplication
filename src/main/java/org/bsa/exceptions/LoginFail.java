package org.bsa.exceptions;

public class LoginFail extends Exception {
    public LoginFail() {
        super(String.format("Incorrect credentials"));
    }
}