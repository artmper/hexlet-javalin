package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.sql.SQLException;
import java.util.Optional;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {
    public static void build(Context ctx) {
        ctx.render("sessions/build.jte");
    }

    public static void create(Context ctx) throws SQLException {
        var nickname = ctx.formParam("nickname");
        var password = ctx.formParam("password");

        Optional<User> maybeUser = UserRepository.findByName(nickname);

        if (maybeUser.isEmpty() || !maybeUser.get().getPassword().equals(password)) {
            ctx.sessionAttribute("flash", "Username or password is incorrect!");

            String flash = ctx.consumeSessionAttribute("flash");
            var page = new BuildUserPage();
            page.setFlash(flash);
            ctx.status(422);
            ctx.render("sessions/build.jte", model("page", page));
            return;
        }

        ctx.sessionAttribute("currentUser", nickname);
        ctx.redirect("/");
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
}
