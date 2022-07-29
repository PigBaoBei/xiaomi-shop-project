package com.qf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.entity.User;

import java.io.IOException;

public class JSON<T> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String stringify(Object object){
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
         } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 序列化
     * @param t
     * @return
     * @param <T>
     */
    public static<T> String stringify2(T t){
        try {
            return OBJECT_MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化
     * @param s
     * @param t
     * @return
     * @param <T>
     * @throws IOException
     */
    public static <T> T parse(String s, Class<T> t) throws IOException {
        return  OBJECT_MAPPER.readValue(s, t );
    }

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setGender("男");
        user.setCode("234234324");
        user.setUsername("jack");
        String s = stringify2(user);
        User user2 = parse(s, User.class);
        System.out.println(user2);
        System.out.println();
    }

}
