package ru.ob11to.basejava.storage;

import ru.ob11to.basejava.exception.ExistStorageException;
import ru.ob11to.basejava.exception.NotExistStorageException;
import ru.ob11to.basejava.exception.StorageException;
import ru.ob11to.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000; //size mass

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];  //место хранения
    protected int size = 0;  //размер массива

    public int size() {
        return size;
    } //размер массива

    public void clear() { //чистим массив, пробегаем по всем элементам и удаляем резюме
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) { //в дальнейшем будет реализовывать изменение в резюме
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) { // расширяем массив на 1 и добавляем резюме
        int index = getIndex(r.getUuid()); // получаем число, по которому будем сравнивать, есть ли данное резюме
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (size > STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            //пропишем логику
            insertElement(r, index);
            size++; // увеличиваем размер массива
            // System.out.println(size);
        }
    }


    public void delete(String uuid) { //происходит удаление резюме
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            System.out.println(index);
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) { // пробегаем по массиву, если резюме совпадают, возвращаем его.
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in ru.ob11to.basejava.storage (without null)
     * массив, содержит только резюме в хранилище (без null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}
