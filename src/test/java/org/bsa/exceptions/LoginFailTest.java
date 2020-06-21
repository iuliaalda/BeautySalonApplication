package org.bsa.exceptions;

import org.bsa.service.UserService;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class LoginFailTest {
    @Test(expected = LoginFail.class)
    public void testLoginFail() throws Exception{
        File wrfile = new File("src\\main\\resources\\users.json");
        UserService.loadUsersFromFile(wrfile);
        UserService.checkLoginCredentials("A","test","Customer");
    }
}