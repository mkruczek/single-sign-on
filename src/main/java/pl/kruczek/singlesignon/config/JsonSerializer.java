package pl.kruczek.singlesignon.config;

import com.google.gson.Gson;

public class JsonSerializer {

    static Gson gson = new Gson();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }
}
