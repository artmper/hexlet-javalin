package org.example.hexlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.RootController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.repository.BaseRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        Javalin app = getApp();

        app.before(ctx -> {
            ctx.contentType("text/html; charset=UTF-8");
        });

        app.before(ctx -> {
            System.out.println("Request received at: " + LocalDateTime.now());
        });

        app.get(NamedRoutes.rootPath(), RootController::index);

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        app.post(NamedRoutes.usersPath(), UsersController::create);

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);

        app.get(NamedRoutes.buildSessionPath(), SessionsController::build);
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);
        app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);

        app.start(7070);
    }

    public static Javalin getApp() throws Exception {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:hexlet_javalin;DB_CLOSE_DELAY=-1");

        var dataSource = new HikariDataSource(hikariConfig);
        String sql;
        try (var url = HelloWorld.class.getClassLoader().getResourceAsStream("schema.sql")) {
            sql = new BufferedReader(new InputStreamReader(Objects.requireNonNull(url)))
                    .lines().collect(Collectors.joining("\n"));
        }

        try (var conn = dataSource.getConnection();
                var stmt = conn.createStatement()) {
            stmt.execute(sql);
        }

        BaseRepository.dataSource = dataSource;

        return Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
    }
}
