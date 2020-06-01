package com.epam.bh.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {


    public <T> List<T> readObjectsFromJsonFile(String filePath, Class<T> clazz) {
        List<T> objectsListFromJsonFile = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream jsonFile = new FileInputStream(filePath);
            objectsListFromJsonFile = mapper.readValue(jsonFile, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectsListFromJsonFile;
    }

    public <T> void writeObjectsToJsonFile(List<T> t, String filePath) {
        try (FileWriter jsonFile = new FileWriter(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(t);
            jsonFile.write(json);
            jsonFile.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
