package ru.ob11to.basejava;

import ru.ob11to.basejava.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();

        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);
        collection.add(RESUME_4);

        for (Resume resume : collection) {
            System.out.println(resume);
           // if (Objects.equals(resume.getUuid(), UUID_1)) {
//                collection.remove(r); нельзя, неявное преобразование
        }

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            System.out.println(resume);
            if (Objects.equals(resume.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection);

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        System.out.println();
        // Bad!
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        System.out.println();

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }

      //  List<Resume> list = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        //list.remove(1); нельзя UnsupportedOperationException
        System.out.println();

        String[] skills = {"Java, Spring, Maven"};
        Developer developers = new Developer("GG", skills);
        IteratorCustom iteratorCustom = developers.getIterator();
        while(iteratorCustom.hasNext()){
            System.out.println(iteratorCustom.next());
        }


    }
}

// pattern iterator test

interface IteratorCustom {
    boolean hasNext();

    Object next();

}

interface CollectionCustom{
    IteratorCustom getIterator();
}

class Developer implements CollectionCustom {
    private final String name;
    private final String[] skills;

    public Developer(String name, String[] skills) {
        this.name = name;
        this.skills = skills;
    }


    @Override
    public IteratorCustom getIterator() {
        return new SkillIterator();
    }

    public String getName() {
        return name;
    }

    private  class SkillIterator implements IteratorCustom {
        int index;

        @Override
        public boolean hasNext() {
            return index < skills.length;
        }

        @Override
        public Object next() {
            return skills[index++];
        }
    }

}













