package org.example.hexlet.dto.users;

import org.example.hexlet.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UsersPage {
    private List<User> users;
    private String header;
    private String term;
}