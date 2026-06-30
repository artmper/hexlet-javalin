package org.example.hexlet;

import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;

import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
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

        app.get(NamedRoutes.usersPath(), ctx -> {
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

        app.post(NamedRoutes.usersPath(), ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();

            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают!")
                        .get();
                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", model("page", page));
            }
        });

        app.get(NamedRoutes.buildUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", model("page", page));
        });

        app.get(NamedRoutes.buildCoursePath(), ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/build.jte", model("page", page));
        });

        app.get(NamedRoutes.userPath("{id}"), ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = UserRepository.getEntities().stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User not found"));
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

        app.get(NamedRoutes.coursePath("{id}"), ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = CourseRepository.getEntities().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get(NamedRoutes.coursesPath(), ctx -> {
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

        app.post(NamedRoutes.coursesPath(), ctx -> {
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
        });

        app.start(7070);
    }
}
