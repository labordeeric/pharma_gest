package com.pharma.app.pharmaproto.utils;
//@Warning
// l'utilisation de reflection peut degrader la performance
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ModelBuilder<T> {
    //declaration de classe Modèle
    private final Class<T> clazz;
    //declaration de Map String comme nom de champ
    //et Object comme valeur de champ
    private final Map<String, Object> properties;
    //Constructeur de classe
    public ModelBuilder(Class<T> clazz) {
        this.clazz = clazz;
        this.properties = new HashMap<>();
    }
    //reference de setter sous forme de Map ("String", Object)
    public ModelBuilder<T> set(String key, Object value) {
        properties.put(key, value);
        return this;
    }
    //la fonction build pour construire l'instance initialisé de classe
    public T build() {
        T instance = null;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                String name = field.getName();
                Object value = properties.get(name);
                if (value != null) {
                    field.setAccessible(true);
                    field.set(instance, value);
                }
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            // log the exception here
            System.out.printf("Error building instance of { %s } : { %s } " , clazz.getSimpleName(), e.getMessage());
        }
        return instance;
    }
}
