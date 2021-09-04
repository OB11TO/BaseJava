package ru.ob11to.basejava.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " no exist😈", uuid);
    }
}
