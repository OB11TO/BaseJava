package ru.ob11to.basejava.storage;

import ru.ob11to.basejava.exception.StorageException;
import ru.ob11to.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000; //size mass
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];  //место хранения
    protected int size = 0;  //размер массива


    protected abstract Integer getSearchKey(String uuid);//Поиск позиции резюме в зависимости от реализации

    protected abstract void fillDeletedElement(int index);//Реализация удаления резюме

    protected abstract void insertElement(Resume r, int index);//Реализация добавления резюме

    /* Вернет размер массива */
    public int size() {
        return size;
    }

    /* Чистим массив, пробегаем по всем элементам и удаляем резюме*/
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /*Обновляет резюме*/
    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[ index] = r;
    }

    /* Не используем
    public void update(Resume r) { //в дальнейшем будет реализовывать изменение в резюме
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }*/

    /*Сохраняет новое резюме*/
    @Override
    protected void doSave(Resume r, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            //пропишем логику
            insertElement(r,  index);
            size++; // увеличиваем размер массива
            // System.out.println(size);
        }
    }

    /*Удаляет резюме*/
    @Override
    protected void doDelete(Integer index) {
        System.out.println(index);
        fillDeletedElement( index);
        storage[size - 1] = null;
        size--;
    }

    /*Выводит резюме*/
    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

   /* не используем
    public Resume get(String uuid) { // пробегаем по массиву, если резюме совпадают, возвращаем его.
        int index = getSearchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }*/

    /**
     * @return array, contains only Resumes in ru.ob11to.basejava.storage (without null)
     * массив, содержит только резюме в хранилище (без null)
     * вернет массив резюме
     */
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    /*true - резюме существует в массиве*/
    @Override
    protected boolean isExist(Integer index) {
        return  index >= 0;
    }

}
