import java.util.ArrayList;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];  //место хранения
    private int size = 0;  //размер массива

    void clear() { //чистим массив, пробегаем по всем элементам и удаляем резюме
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }


    void save(Resume r) { // расширяем массив на 1 и добавляем резюме
        storage[size] = r;
        size++;
    }


    Resume get(String uuid) { // пробегаем по массиву, если резюме совпадают, возвращаем его.
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if(uuid.equals(storage[i].uuid)){
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size --;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     * массив, содержит только резюме в хранилище (без null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size];
        for (int i = 0; i < size; i++) {
            result[i] = storage[i];
        }
        return result;
    }

    int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }
}
