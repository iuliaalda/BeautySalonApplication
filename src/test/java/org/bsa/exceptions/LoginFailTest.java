package org.bsa.exceptions;

import org.bsa.service.UserService;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginFailTest {
    @Test(expected = LoginFail.class)
    public void testLoginFail() throws Exception{
        UserService.loadUsersFromFile();
        UserService.checkLoginCredentials("A","test","Customer");
    }
}