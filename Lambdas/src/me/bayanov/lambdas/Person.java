package me.bayanov.lambdas;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        if (name == null) {
            throw new NullPointerException("Name must be not null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age must be >= 0");
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
