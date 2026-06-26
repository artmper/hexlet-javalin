package org.example.hexlet;

import org.apache.commons.lang3.StringUtils;

import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;

import static io.javalin.rendering.template.TemplateUtil.model;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.before(ctx -> {
            ctx.contentType("text/html; charset=UTF-8");
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var header = "All Users";
            var term = ctx.queryParam("term");

            if (term != null) {
                users = users.stream()
                        .filter(u -> StringUtils.containsIgnoreCase(u.getName(), term))
                        .toList();
            }

            var page = new UsersPage(users, header, term);
            ctx.render("users/index.jte", model("page", page));
        });

        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            var password = ctx.formParam("password");
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
        });

        app.get("/hello", ctx -> {
            String name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name + "!");
        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            var userId = ctx.pathParam("id");
            var postId = ctx.pathParam("postId");
            ctx.result("User ID: " + userId + ", Post ID: " + postId);
        });

        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.get("/courses/build", ctx -> {
            ctx.render("courses/build.jte");
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = UserRepository.getEntities().stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User not found"));
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = CourseRepository.getEntities().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get("/courses", ctx -> {
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
        });

        app.post("/courses", ctx -> {
            var name = ctx.formParam("name").trim();
            var description = ctx.formParam("description");

            var course = new Course(name, description);
            CourseRepository.save(course);
            ctx.redirect("/courses");
        });

        app.start(7070);
    }
}
