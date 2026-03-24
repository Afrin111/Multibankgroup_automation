package com.multibank.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class DataProviderUtil {

    private static final ObjectMapper mapper   = new ObjectMapper();
    private static final String       dataPath = "src/test/resources/testdata/";

    public static JsonNode loadJson(String fileName) {
        try {
            return mapper.readTree(new File(dataPath + fileName));
        } catch (IOException e) {
            throw new RuntimeException("Cannot load: " + fileName + " — " + e.getMessage());
        }
    }

    public static JsonNode getNode(String fileName, String nodeName) {
        return loadJson(fileName).get(nodeName);
    }
}