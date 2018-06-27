package com.jyz.childrenwatch.utils;

import com.google.gson.Gson;

public class JsonSerializerUtil {
    static Gson gson;

    static {
        gson = new Gson();
    }

    public static String toJson(Object object) {
        return  gson.toJson(object);
    }

}
