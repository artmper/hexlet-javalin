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
            <h1 class="text-body-emphasis">Привет, Хекслет!</h1>
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