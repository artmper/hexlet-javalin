package org.example.hexlet;

import org.apache.commons.lang3.StringUtils;

import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/users", ctx -> ctx.result("GET /users"));

        app.post("/users", ctx -> ctx.result("POST /users"));

        app.get("/hello", ctx -> {
            String name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!");
        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            var userId = ctx.pathParam("id");
            var postId = ctx.pathParam("postId");
            ctx.result("User ID: " + userId + ", Post ID: " + postId);
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = Data.getCourses().stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
            List<Course> courses;
            var header = "Programming languages courses";
            var term = ctx.queryParam("term");

            if (term != null) {
                courses = Data.getCourses().stream()
                        .filter(c -> StringUtils.containsIgnoreCase(c.getName(), term) ||
                                StringUtils.containsIgnoreCase(c.getDescription(), term))
                        .toList();
            } else {
                courses = Data.getCourses();
            }

            var page = new CoursesPage(courses, header, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.start(7070);
    }
}
