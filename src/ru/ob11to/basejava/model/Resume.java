package ru.ob11to.basejava.model;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier (уникальный индентификатор)
   private String uuid;

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

   public void setUuid(String uuid)
   {
      this.uuid= uuid;
   }

    @Override
    public String toString() {
        return uuid;
    }
}
