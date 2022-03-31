package ru.ob11to.basejava;

import ru.ob11to.basejava.model.SectionType;

public class TestSingleton {
    private static TestSingleton instance;

    private TestSingleton() {
    }

    public static TestSingleton getInstance() {
        if(instance == null){
            instance = new TestSingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
        Singleton instance = Singleton.valueOf("INTERFACE");
        System.out.println(instance.name()); //элемент

        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }

    }
    public enum Singleton {
        INSTANCE,
        INTERFACE
    }
}
