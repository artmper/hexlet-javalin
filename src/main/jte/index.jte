@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hello Hexlet!</title>
    </head>
    <body>
        <main>
            <h1>Привет, Хекслет!</h1>
            @if(page.getCurrentUser() != null)
                <p>Добро пожаловать, ${page.getCurrentUser()}.</p>
                <form action="/session/destroy" method="post">
                    <input type="submit" value="Выход">
                </form>
            @endif
            <div style="display: flex; justify-content: center;">
                <a href="${NamedRoutes.buildSessionPath()}">Логин</a>
            </div>
            @if (!page.isVisited())
                <h2>Тестирую куки...</h2>
            @endif
            <ul>
                <li><a href="${NamedRoutes.coursesPath()}">Courses</a></li>
                <li><a href="${NamedRoutes.usersPath()}">Users</a></li>
            </ul>
        </main>
    </body>
</html>