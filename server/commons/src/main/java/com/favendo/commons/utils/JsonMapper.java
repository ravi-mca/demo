package com.favendo.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;

public final class JsonMapper {

    private JsonMapper() {
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJSON(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            //throw new Exception("error occurred while converting object to json", exception);
        }
        return null;
    }

    public static <T> T jsonToObject(String json, Class<T> targetClass) {
        try {
            return objectMapper.readValue(json, targetClass);
        } catch (IOException exception) {
            exception.printStackTrace();
            //throw new Exception("error occurred while converting json to object", exception);
        }
        return null;
    }

    public static <T> List<T> jsonToList(String json, Class<T> targetClass) {
        try {
            return objectMapper.readValue(json,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, targetClass));
        } catch (IOException exception) {
            exception.printStackTrace();
            //throw new Exception("error occurred while converting json to list", exception);
        }
        return null;
    }
}