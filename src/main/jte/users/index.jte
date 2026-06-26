@import org.example.hexlet.dto.users.UsersPage

@param UsersPage page

@template.layout.page(
title = page.getHeader(),
description = null,
content = @`
    <form action="/users" method="get">
        <input type="search" name="term" value="${page.getTerm()}" />
        <input type="submit" value="Search" />
    </form>
    @if(page.getUsers().isEmpty())
        <p>Пока не добавлено ни одного user</p>
    @else
        @for(var user : page.getUsers())
            <div>
                <h2><a href="/users/${user.getId()}">${user.getName()}</a></h2>
                <p>${user.getEmail()}</p>
            </div>
        @endfor
    @endif
`,
footer = @`<p>My GitHub: <a href="https://github.com/artmper">https://github.com/artmper</a></p>
`
)
