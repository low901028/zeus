package org.apache.realtime.zeus.common.utils;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class MapUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        return objectMapper.readValue(objectMapper.writeValueAsBytes(obj), Map.class);
    }

    public static <T> T jsonStrToObject(String jsonStr, Class<T> clazz) throws IOException {
        return objectMapper.readValue(jsonStr, clazz);
    }

    public static String writeValueAsString(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    private static Object getValue(Map<String, Object> map, String key) {
        if (!map.containsKey(key)) {
            throw new RuntimeException(key + " not exist on  " + GsonUtils.GSON.toJson(map));
        }
        return map.get(key);
    }

    public static void replaceAllElement(
            Map<String, Object> map, final List<String> keys, final Object value)
            throws JsonProcessingException {
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            if (entry.getValue() instanceof Map) {
                replaceAllElement((Map<String, Object>) entry.getValue(), keys, value);
            }
            for (String key : keys) {
                if (key.equalsIgnoreCase(entry.getKey())) {
                    entry.setValue(value);
                }
            }
        }
    }
}
