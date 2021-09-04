package ru.ob11to.basejava.storage;

import ru.ob11to.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) { //сравниваем, есть ли такое резюме в массиве
        Resume searchKey = new Resume(uuid); // создаем с помощью конструктора резюме
        return Arrays.binarySearch(storage, 0, size, searchKey); // возвращает позицию полученного резюме
    }

    @Override
    protected void fillDeletedElement(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected void insertElement(Resume r, int index) {
        int insertIdx = -index - 1;
        System.out.println(insertIdx);
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = r;
    }
}
