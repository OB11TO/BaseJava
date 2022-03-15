package ru.ob11to.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.ob11to.basejava.exception.StorageException;
import ru.ob11to.basejava.model.Resume;


public class AbstractArrayStorageTest extends AbstractStorageTest{

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }
    @Test(expected = StorageException.class)
    //@Test(expected = ArrayIndexOutOfBoundsException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Name" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("Overflow"));
    }
}