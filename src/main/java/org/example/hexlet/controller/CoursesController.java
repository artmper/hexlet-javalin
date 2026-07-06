package org.example.hexlet.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import org.apache.commons.lang3.StringUtils;

import org.example.hexlet.NamedRoutes;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.sql.SQLException;
import java.util.List;

public class CoursesController {
    public static void index(Context ctx) throws SQLException {
        List<Course> courses = CourseRepository.getEntities();
        var header = "Programming languages courses";
        var term = ctx.queryParam("term");

        if (term != null) {
            courses = courses.stream()
                    .filter(c -> StringUtils.containsIgnoreCase(c.getName(), term) ||
                            StringUtils.containsIgnoreCase(c.getDescription(), term))
                    .toList();
        }

        var page = new CoursesPage(courses, header, term);
        ctx.render("courses/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new CoursePage(course);
        ctx.render("courses/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(v -> v.length() > 2, "Название курса должно содержать больше 2 символов!")
                    .get()
                    .trim();
            var description = ctx.formParamAsClass("description", String.class)
                    .check(v -> v.length() > 10, "Описание не может быть короче 10 символов!")
                    .get();
            var course = new Course(name, description);
            CourseRepository.save(course);
            ctx.redirect(NamedRoutes.coursesPath());
        }
        catch (ValidationException e) {
            var page = new BuildCoursePage(e.getErrors());
            ctx.render("courses/build.jte", model("page", page));
        }
    }
}
