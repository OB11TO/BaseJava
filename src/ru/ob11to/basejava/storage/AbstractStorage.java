package ru.ob11to.basejava.storage;

import ru.ob11to.basejava.exception.ExistStorageException;
import ru.ob11to.basejava.exception.NotExistStorageException;
import ru.ob11to.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Task 5.1
 */
//SK - S
public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = getLogger(AbstractStorage.class.getName());

    protected abstract boolean isExist(SK searchKey); //метод для проверки существования резюме (должно быть true)

    protected abstract void doUpdate(Resume r, SK searchKey);//реализация update

    protected abstract void doSave(Resume r, SK searchKey); // реализация save

    protected abstract void doDelete(SK searchKey); //реализация delete

    protected abstract Resume doGet(SK searchKey); //реализация get

    protected abstract SK getSearchKey(String uuid);//для поиска существования резюме

    protected abstract List<Resume> doCopyAll();

    public void update(Resume r) { //в дальнейшем будет реализовывать изменение в резюме
        /* возвращает сначала String резюме, а затем идет поиск индекса в резюме */
        LOG.info("Update" + r);
        SK searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }


    public void save(Resume r) { // расширяем массив на 1 и добавляем резюме
        LOG.info("Save" + r);
        SK searchKey = getNotExistedSearchKey(r.getUuid()); // получаем число, по которому будем сравнивать, есть ли данное резюме
        doSave(r, searchKey);

    }


    public void delete(String uuid) { //происходит удаление резюме
        LOG.info("Delete" + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);

    }


    public Resume get(String uuid) {
        LOG.info("Get" + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }


    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " no exist))))))");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
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
