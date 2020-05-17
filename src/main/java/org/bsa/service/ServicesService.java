package org.bsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.bsa.model.Employee;
import org.bsa.model.Service;
import org.bsa.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ServicesService {
    private static List<Service> services;
    private static final Path S_PATH=FileSystemService.getPathToFile("config","\\services.json");
    public static void addServices(){
        ArrayList<Service> services= new ArrayList<>();
        Service s1 = new Service("Makeup",50);
        Service s2 = new Service("Haircut",40);
        services.add(s1);
        services.add(s2);

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("src/main/resources/services.json");
            objectMapper.writeValue(file,services);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void loadServices() throws IOException{
        ServicesService.addServices();
        if(!Files.exists(S_PATH)){
            FileUtils.copyURLToFile(Service.class.getClassLoader().getResource("\\services.json"), new File("src/main/resources/services.json"));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/services.json");
        services=objectMapper.readValue(file, new TypeReference<List<Service>>() {});
        System.out.println("Read services"+services);
    }

}
