package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DataLoader {
    public static Map<String, Object> loadTestData(String resourcePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Test data not found: " + resourcePath);
            }
            return mapper.readValue(inputStream, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }
}
