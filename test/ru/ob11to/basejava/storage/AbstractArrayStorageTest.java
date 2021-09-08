package ru.ob11to.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.ob11to.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    /**
     * Массив
     */
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    //?????
    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    /**
     * Чиститься и инициализируется массив
     * Создаются 3 новый резюме
     *
     */
    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    /**
     * Проверка на размер. Вызывается функция, которая проверит.
     *
     */
    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    /**
     * Проверка на размер.
     * assertEquals сравнивает размер, который передали(изначальный, который инициализировали
     * с размером массива)
     *
     * @param size размер массива изначального
     */
    private void assertSize(int size) throws Exception {
        assertEquals(size, storage.size());
    }

    /**
     * Сначала чистит массив, а затем проверяет пустой ли он.
     *
     */
    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    /**
     * Создаем новый резюме.
     * Вызываем функцию update.
     * Сравниваем заменилась ли она новым значением.
     *
     */
    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    /**
     * Добавим этот резюме в массив.
     * Проверим, увеличился размер в массиве.
     * Вызываем функцию, которая проверит, находится ли данное резюме в массиве.
     */
    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    /**
     * Первый параметр - это RESUME_4, которое создали для проверки.
     * Второй параметр - это RESUME_4, которое положили в массив storage.
     * @param resume  RESUME_4
     */
    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void delete() throws Exception{
    }

    @Test
    public void get() throws Exception{
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);

    }

    @Test
    public void getAll() throws Exception {
    }
}