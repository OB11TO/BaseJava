package ru.ob11to.basejava;

import ru.ob11to.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume( "Name");
        Class<? extends Resume> resumeClass = r.getClass();
        Field field = resumeClass.getDeclaredFields()[0];

        System.out.println(field.getName());
        field.setAccessible(true);

        System.out.println(field.get(r));
        field.set(r, "newUuid");
        System.out.println(r + "\n");

        Method method = resumeClass.getMethod("toString");
        System.out.println(method.getName());
        System.out.println(method.invoke(r));


    }
}
