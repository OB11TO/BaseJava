package ru.ob11to.basejava;

import ru.ob11to.basejava.model.Resume;
import ru.ob11to.basejava.storage.ArrayStorage;
import ru.ob11to.basejava.storage.SortedArrayStorage;

/**
 * ru.ob11to.basejava.Test for your ru.ob11to.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    final static ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    final static SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        // r1.setUuid("uuid1");
        Resume r2 = new Resume("uuid2");
        // r2.setUuid("uuid2");
        Resume r3 = new Resume("uuid3");
        //  r3.setUuid("uuid3");
        Resume r4 = new Resume("uuid4");
        //  r4.setUuid("uuid4");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        ARRAY_STORAGE.update(r1);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        // System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());


        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r4);
        System.out.println("Get r1: " + SORTED_ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());
        System.out.println();
        printAll();
        SORTED_ARRAY_STORAGE.delete(r2.getUuid());
        printAll();

    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
