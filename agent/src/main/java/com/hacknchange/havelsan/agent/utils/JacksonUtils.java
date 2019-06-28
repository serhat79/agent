package com.hacknchange.havelsan.agent.utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonUtils {


    private static ObjectMapper ourInstance  ;

    private static ObjectMapper getInstance() {
        if (ourInstance == null) {
            ourInstance = new ObjectMapper();
        }
        return ourInstance;
    }

    private JacksonUtils() {
    }


    public static <T> T readValue(String value, Class<T> clazz){
        try {
            return getInstance().readValue(value, clazz);
        } catch (Exception e) {

        }
        return null;
    }

    public static <T> T readValue(String value, TypeReference<T> typeReference){
        try {
            return getInstance().readValue(value, typeReference);
        } catch (Exception e) {

        }
        return null;
    }

    public static String writeValueAsString(Object object){
        try {
            return getInstance().writeValueAsString(object);
        } catch (Exception e) {

        }
        return null;
    }

    public static String writeValueAsString2(String value){
        try {
            return getInstance().writeValueAsString(value);
        } catch (Exception e) {

        }
        return null;
    }
    public static void writeObjectToFile(String path,Object value){
        try {
           getInstance().writeValue(new File(path),value);
        } catch (Exception e) {

        }

    }

    public static <T> List<T>  readValueToList(String value, Class<T> clazz){
        try {
            return  getInstance().readValue(value,  getInstance().getTypeFactory().constructCollectionType(List.class,clazz));
        } catch (Exception e) {

        }
        return null;
    }
    public static <T> List<T>  readValueToList(File file, Class<T> clazz){
        try {
            return  getInstance().readValue(file,  getInstance().getTypeFactory().constructCollectionType(List.class,clazz));
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
    }

    public static <T> T readProperties(String jsonValue, String properties) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, T> myMap;
            myMap = objectMapper.readValue(jsonValue, HashMap.class);
            return myMap.get(properties);
        } catch (IOException e) {
            //e.printStackTrace();

        }
        return null;
    }

}
