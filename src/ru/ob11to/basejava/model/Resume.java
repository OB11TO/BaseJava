package ru.ob11to.basejava.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier (уникальный идентификатор)
    private final String uuid;

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(final String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
