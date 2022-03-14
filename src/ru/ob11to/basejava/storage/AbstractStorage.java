package ru.ob11to.basejava.storage;

import ru.ob11to.basejava.exception.ExistStorageException;
import ru.ob11to.basejava.exception.NotExistStorageException;
import ru.ob11to.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

/**
 * Task 5.1
 */

public abstract class AbstractStorage implements Storage {

    protected abstract boolean isExist(Object searchKey); //метод для проверки существования резюме (должно быть true)

    protected abstract void doUpdate(Resume r, Object searchKey);//реализация update

    protected abstract void doSave(Resume r, Object searchKey); // реализация save

    protected abstract void doDelete(Object searchKey); //реализация delete

    protected abstract Resume doGet(Object searchKey); //реализация get

    protected abstract Object getSearchKey(String uuid);//для поиска существования резюме

    protected abstract List<Resume> doCopyAll();

    public void update(Resume r) { //в дальнейшем будет реализовывать изменение в резюме
        /* возвращает сначала String резюме, а затем идет поиск индекса в резюме */
        Object searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }


    public void save(Resume r) { // расширяем массив на 1 и добавляем резюме
        Object searchKey = getNotExistedSearchKey(r.getUuid()); // получаем число, по которому будем сравнивать, есть ли данное резюме
        doSave(r, searchKey);

    }


    public void delete(String uuid) { //происходит удаление резюме
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);

    }


    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }


    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

}
