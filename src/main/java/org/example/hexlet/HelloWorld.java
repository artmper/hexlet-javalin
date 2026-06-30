package org.example.hexlet;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;


public class HelloWorld {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

//        app.before(ctx -> {
//            ctx.contentType("text/html; charset=UTF-8");
//        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        app.post(NamedRoutes.usersPath(), UsersController::create);

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);

        app.start(7070);
    }
}
