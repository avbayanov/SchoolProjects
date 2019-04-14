package me.bayanov.lambdasMain;

import me.bayanov.lambdas.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Иван", 16));
        persons.add(new Person("Сергей", 20));
        persons.add(new Person("Пётр", 15));
        persons.add(new Person("Сергей", 21));
        persons.add(new Person("Александр", 22));

        // Список уникальных имён
        List<String> uniqueNames = persons.stream()
                .map(p -> p.getName())
                .distinct()
                .collect(toList());

        // Вывод списка уникальных имён в формате: Имена: Имя1, Имя2.
        String uniqueNamesForPrint = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesForPrint);

        // Получить список людей младше 18, посчитать для них средний возраст
        List<Person> personsUnder18 = persons.stream()
                .filter(p -> p.getAge() < 18)
                .collect(toList());
        OptionalDouble averageAgeOfPersonsUnder18 = personsUnder18.stream()
                .mapToInt(p -> p.getAge())
                .average();
        averageAgeOfPersonsUnder18.ifPresent((p) -> System.out.println("Средний возраст людей младше 18: " + p));

        // При помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(p -> p.getName(), averagingInt(p -> p.getAge())));

        // Получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        List<Person> sortedPersonsFrom20To45 = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .collect(toList());
        sortedPersonsFrom20To45.stream()
                .map(p -> p.getName())
                .forEach(System.out::println);

        // Вывести n корней чисел
        DoubleStream squareRoots = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сколько корней чисел вывести?");
        int forRoots = scanner.nextInt();
        squareRoots.limit(forRoots).forEach(System.out::println);
    }
}
