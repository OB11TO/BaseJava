package ru.ob11to.basejava;


import java.lang.annotation.*;
import java.lang.reflect.Field;


@Game(name = "Крикет", day = "воскресенье")
@Game(name = "Крикет2", day = "воскресенье2")

@Reflectable(name = "reflectable", value = "какие-то метаданные")
@Game(name = "Крикет3", day = "воскресенье3")
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Games games = Test.class.getAnnotation(Games.class);

        Class<Test> aClass = Test.class;
        Annotation[] annotations = aClass.getAnnotations();

        for (Annotation annotation : annotations) {
            //System.out.println(Arrays.toString(annotations));
            System.out.println();
            if (annotation instanceof Reflectable) {
                Reflectable mAnnotation = (Reflectable) annotation;
                System.out.println("name: " + mAnnotation.name());
                System.out.println("value: " + mAnnotation.value());
            }
            if (annotation instanceof Games) {
                Games gAnnotation = (Games) annotation;
                for (Game game : gAnnotation.value()) {
                    System.out.println(game.name() + " " + game.day());
                }
            }
        }

        Class<Hunter> mainClass = Hunter.class;
        Class<?> resume = Class.forName("ru.ob11to.basejava.model.Resume");
        Hunter instance = new Hunter(10, "hi", 1);

        Field field = mainClass.getDeclaredField("fill");
        String nameField = field.getName();
        Object fieldType = field.getType();
        field.setAccessible(true);
        //Object value = field.get(instance);

        int a = (int) field.get(instance);
        System.out.println(a);

        System.out.println(nameField);  // получаем название поля
        System.out.println(fieldType); // получает тип поля

        String nameMainClass = mainClass.getName();
        String nameResume = resume.getName();
        int classModifiers = mainClass.getModifiers();


        System.out.println(nameMainClass + " " + nameResume);
        System.out.println(classModifiers);
        System.out.println(" ");

        System.out.println(games);
        for (Game game : games.value()) {
            System.out.println(game.name() + " " + game.day());
        }

        Field[] game = Test.class.getDeclaredFields();
        //System.out.println(game);
        for (Field f : game) {
            System.out.println(f.getGenericType());
        }
    }
}


class Hunter {
    private final int fill;
    private final String pull;
    private final int put;


    public Hunter(int fill, String pull, int put) {
        this.fill = fill;
        this.pull = pull;
        this.put = put;
    }
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Reflectable {
    String name();

    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Games {
    Game[] value();
}

@Repeatable(Games.class)
@interface Game {
    String name() default "Что-то под вопросом";

    String day();
}
