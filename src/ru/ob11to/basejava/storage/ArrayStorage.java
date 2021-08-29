package ru.ob11to.basejava.storage;

import ru.ob11to.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based ru.ob11to.basejava.storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];  //место хранения
    private int size = 0;  //размер массива

    public void clear() { //чистим массив, пробегаем по всем элементам и удаляем резюме
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Resume " + r.getUuid() + " no exist👻");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) { // расширяем массив на 1 и добавляем резюме
        int index = getIndex(r.getUuid());
        if (!(index == -1)) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size > STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }


    public Resume get(String uuid) { // пробегаем по массиву, если резюме совпадают, возвращаем его.
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " no exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " no exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }



       /* for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }*/
    }

    /**
     * @return array, contains only Resumes in ru.ob11to.basejava.storage (without null)
     * массив, содержит только резюме в хранилище (без null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {  // пробегаемся по списку и сравниваем нужное резюме
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
