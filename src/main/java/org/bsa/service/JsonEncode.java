package org.bsa.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.bsa.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.bsa.service.UserService.encodePassword;


public class JsonEncode extends UserService {

    public static void main(String argv[]) {

        ArrayList<User> users = new ArrayList<>();
        User u1 = new User("Iulia",encodePassword("Iulia", "abcde"), "Employee");
        User u2 = new User("Bia",encodePassword("Bia", "xyz"), "Employee");
        User u3 = new User("B",encodePassword("B", "bbb"), "Customer");
        User u4 = new User("A",encodePassword("A", "aaa"), "Customer");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);

        Path USERS_PATH = FileSystemService.getPathToFile("config", "\\users.json");


        try{
            ObjectMapper objectMapper = new ObjectMapper();
            //objectMapper.writeValue(new File("C:\\Users\\aldai\\Desktop\\Project\\BSApplication\\src\\main\\resources\\users.json"),users);
            objectMapper.writeValue(new File("src/main/resources/users.json"),users);
            //objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(),users);
        }catch (IOException e){e.printStackTrace();}

    }


    }




