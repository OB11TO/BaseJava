package ru.ob11to.basejava.storage;

import ru.ob11to.basejava.model.Resume;

/**
 * Array based ru.ob11to.basejava.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    @Override
    protected int getIndex(String uuid) {  // пробегаемся по списку и сравниваем нужное резюме
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size -1];

    }

    @Override
    protected void insertElement(Resume r,int index) {
        storage[size] = r; // добавляем резюме массиву
    }
}
