package org.bsa.service;

import org.apache.commons.io.FileUtils;
import org.bsa.exceptions.InvalidRole;
import org.bsa.exceptions.LoginFail;
import org.bsa.model.Employee;
import org.bsa.model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class UserServiceTest {
    File usrfile = new File("src\\test\\resources\\users.json");
    @BeforeClass
    public static void beforeClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER=".test-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        FileUtils.copyURLToFile(User.class.getClassLoader().getResource("users.json"),new File("src/test/resources/users.json"));
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        FileUtils.copyURLToFile(User.class.getClassLoader().getResource("users.json"),new File("src/test/resources/users.json"));

    }
    @Test
    public void loadUsersFromFile() throws IOException {
        UserService.loadUsersFromFile(usrfile);
        assertNotNull(UserService.users);
        assertEquals(4,UserService.users.size());
    }

    @Test
    public void checkLoginCredentials() throws LoginFail, InvalidRole {
            String usr="Iulia";
            String password="abcde";
            UserService.checkLoginCredentials(usr,password,"Employee");
            assertNotNull(UserService.users);

    }
    @Test(expected = InvalidRole.class)
    public void testInvalidRole() throws Exception {
        String usr="Iulia";
        String password="abcde";
        assertNotNull(UserService.users);
        UserService.checkLoginCredentials(usr,password,"");

    }
    @Test(expected = LoginFail.class)
    public void testLoginFail() throws Exception, InvalidRole {
        String usr="Iulia";
        String password="";
        assertNotNull(UserService.users);
        UserService.checkLoginCredentials(usr,password,"customer");

    }

    @Test
    public void encodePassword() {
        assertNotEquals("xyz", UserService.encodePassword("Iulia", "xyz"));


    }
}