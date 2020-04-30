package org.bsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.bsa.exceptions.EmptyFieldException;
import org.bsa.exceptions.LoginFail;
import org.bsa.model.User;
import org.apache.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static List<User> users;
    private static final Path USERS_PATH= FileSystemService.getPathToFile("config", "users.json");


    public static void loadUsersFromFile()throws IOException{
        if(!Files.exists(USERS_PATH)){
            FileUtils.copyURLToFile(User.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    private static void checkEmptyField(String username, String password) throws EmptyFieldException {
        if(username.equals("") || password.equals("")) throw new EmptyFieldException();
    }
    public static void checkLoginCredentials(String username,String password) throws LoginFail {
        String encodePassword=encodePassword(username, password);
        int sw=0;
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                sw=1;
                if (!Objects.equals(encodePassword, user.getPassword()))
                    throw new LoginFail();

            }
        }
        if(sw==0) throw new LoginFail();
    }

    static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }



}
