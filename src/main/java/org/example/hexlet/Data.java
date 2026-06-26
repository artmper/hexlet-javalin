package org.example.hexlet;

import org.example.hexlet.model.Course;

import java.util.Random;
import net.datafaker.Faker;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Collections;

public class Data {
    private static final int ITEMS_COUNT = 12;

    public static List<Course> getCourses() {
        Random random = new Random(124);
        Faker faker = new Faker(random);

        List<String> ids = IntStream
                .range(1, ITEMS_COUNT + 1)
                .mapToObj(i -> Integer.toString(i))
                .collect(Collectors.toList());
        Collections.shuffle(ids, random);

        List<Course> courses = new ArrayList<>();

        for (int i = 0; i < ITEMS_COUNT; i++) {
            Course course = new Course(faker.programmingLanguage().name(), faker.programmingLanguage().creator());
            course.setId(Long.parseLong(ids.get(i)));
            courses.add(course);
        }

        return courses;
    }
}