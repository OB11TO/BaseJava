package ru.ob11to.basejava.storage;

import ru.ob11to.basejava.model.Resume;

import java.util.List;

public interface Storage {
    /*
    Чистка резюме
     */
    void clear();

    /*
    Обновление резюме
     */
    void update(Resume r);

    /*
    Сохранение резюме
     */
    void save(Resume r);

    /*
    Вывести одно резюме на экран
     */
    Resume get(String uuid);

    /*
    Удаление одного резюме
     */
    void delete(String uuid);

    /*
    Вывести все резюме
     */
    //Resume[] getAll();
    List<Resume> getAllSorted();

    /*
    Размер
     */
    int size();
}