package org.example.hexlet.controller;


import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UsersController {
    public static void index(Context ctx) throws SQLException {
        List<User> users = UserRepository.getEntities();
        var header = "All Users";
        var term = ctx.queryParam("term");
        String flash = ctx.consumeSessionAttribute("flash");

        if (term != null) {
            users = users.stream()
                    .filter(u -> StringUtils.containsIgnoreCase(u.getName(), term))
                    .toList();
        }

        var page = new UsersPage(users, header, term);
        page.setFlash(flash);
        ctx.render("users/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        ctx.render("users/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        var name = ctx.formParam("name").trim();
        var email = ctx.formParam("email").trim().toLowerCase();

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают!")
                    .get();
            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.sessionAttribute("flash", "User created successfully!");
            ctx.redirect(NamedRoutes.usersPath());
        } catch (ValidationException e) {
            ctx.sessionAttribute("flash", "Can not create user with these data!");
            var page = new BuildUserPage(name, email, e.getErrors());
            String flash = ctx.consumeSessionAttribute("flash");
            page.setFlash(flash);
            ctx.render("users/build.jte", model("page", page));
        }
    }
}
