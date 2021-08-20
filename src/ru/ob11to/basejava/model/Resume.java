package ru.ob11to.basejava.model;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier (уникальный индентификатор)
   private String uuid;

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
