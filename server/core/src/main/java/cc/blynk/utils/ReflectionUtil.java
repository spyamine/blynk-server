package cc.blynk.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 2/4/2015.
 */
public class ReflectionUtil {

    /**
     * Used to generate map of class fields where key is field value and value is field name.
     */
    public static Map<Integer, String> generateMapOfValueNameInteger(Class<?> clazz) {
        Map<Integer, String> valuesName = new HashMap<>();
        try {
            for (Field field : clazz.getFields()) {
                valuesName.put((Integer) field.get(int.class), field.getName());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return valuesName;
    }

    /**
     * Used to generate map of class fields where key is field value and value is field name.
     */
    public static Map<Short, String> generateMapOfValueNameShort(Class<?> clazz) {
        Map<Short, String> valuesName = new HashMap<>();
        try {
            for (Field field : clazz.getFields()) {
                if (field.getType().isPrimitive()) {
                    valuesName.put((Short) field.get(short.class), field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return valuesName;
    }

    /**
     * Used to set any object property with value via reflection.
     */
    public static boolean setProperty(Object object, String fieldName, String fieldValue) throws Exception {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, castTo(field.getType(), fieldValue));
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return false;
    }

    public static Object castTo(Class type, String value) {
        if (type == byte.class) {
            return Byte.valueOf(value);
        }
        if (type == short.class || type == Short.class) {
            return Short.valueOf(value);
        }
        if (type == int.class || type == Integer.class) {
            return Integer.valueOf(value);
        }
        if (type == long.class) {
            return Long.valueOf(value);
        }
        if (type == boolean.class) {
            return Boolean.valueOf(value);
        }
        return value;
    }
}
