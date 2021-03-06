package ru.ob11to.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.ob11to.basejava.exception.ExistStorageException;
import ru.ob11to.basejava.exception.NotExistStorageException;
import ru.ob11to.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {
    /**
     * Массив
     */
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Name1");

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2, "Name2");

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3, "Name3");

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4, "Name4");


    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    /**
     * Чиститься и инициализируется массив
     * Создаются 3 новых резюме
     */
    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    /**
     * Проверка на размер. Вызывается функция, которая проверит.
     */
    @Test
    public void size() {
        assertSize(3);
    }

    /**
     * Проверка на размер.
     * assertEquals сравнивает размер, который передали (изначальный, который инициализировали
     * с размером массива)
     *
     * @param size размер массива изначального
     */
    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    /**
     * Сначала чистит массив, а затем проверяет пустой ли он.
     */
    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    /**
     * Создаем новое резюме.
     * Вызываем функцию update.
     * Сравниваем заменилась ли она новым значением.
     */
    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1,"New Name");
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    /**
     * Добавим это резюме в массив.
     * Проверим, увеличился размер в массиве.
     * Вызываем функцию, которая проверит, находится ли данное резюме в массиве.
     */
    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }



    /**
     * Первый параметр - это RESUME_4, которое создали для проверки.
     * Второй параметр - это RESUME_4, которое положили в массив storage.
     *
     * @param resume RESUME_4
     */
    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    /**
     * Вызываем метод, который проверит, где находятся эти резюме в storage.
     */
    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);

    }

    /**
     * Создаем массив резюме.
     * Проверяем содержатся ли они в массиве.
     */
    @Test
    public void getAll() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }
}