package com.mydiary.mydiaryprj.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {

    public static String dump(Object data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(data);//일자로출력
            //return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);//출력이쁘게
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T load(String data, Class<T> type) {
        try {
            if (Stringer.isBlank(data)) {
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(data, type);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
