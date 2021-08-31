package ru.ob11to.basejava.storage;

import ru.ob11to.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];  //место хранения
    protected int size = 0;  //размер массива

    public int size() {
        return size;
    }

    public Resume get(String uuid) { // пробегаем по массиву, если резюме совпадают, возвращаем его.
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " no exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
