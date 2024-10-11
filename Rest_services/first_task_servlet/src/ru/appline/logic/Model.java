package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    //описание модели работы, отвечает за респонс
    //будем брать необходимые данные и выводить их на экран

    //данные храним в map (integer,user)
    //ключ:порядковый номер - id,
    //значение ключа:класс user

    private static final Model instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Ivan","Ivanov",11111));
        model.put(2, new User("Aleksei","Alekseev",66666));
        model.put(3, new User("Ilya","Petrov",77777));
        model.put(4, new User("Andrey","Andreev",44444));
    }

    public void add(User user, int id) {
        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }
}
