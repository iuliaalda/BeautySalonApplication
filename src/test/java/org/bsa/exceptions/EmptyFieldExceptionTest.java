package org.bsa.exceptions;

import org.bsa.service.UserService;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmptyFieldExceptionTest {
    @Test(expected = EmptyFieldException.class)
    public void testEmptyField() throws Exception{
        UserService.checkEmptyField("A","");
    }

}