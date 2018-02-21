package com.prueba.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSingleton {

    private Gson gson;

    private static GsonSingleton instance;

    private GsonSingleton(){
        gson = new GsonBuilder().serializeNulls().create();
    }

    public synchronized static GsonSingleton getInstance() {
        if(instance == null) {
            instance = new GsonSingleton();
        }
        return instance;
    }

    public Gson getGson() {
        return gson;
    }

}
