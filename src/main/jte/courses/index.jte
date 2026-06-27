@import org.example.hexlet.dto.courses.CoursesPage

@param CoursesPage page

@template.layout.page(
    title = page.getHeader(),
    description = null,
    content = @`
        <form action="/courses" method="get">
            <input type="search" name="term" value="${page.getTerm()}" />
            <input type="submit" value="Search" />
        </form>
        <a href="/courses/build">
            <label>
                Для создания нового курса,
                нажмите кнопку
                <input type="submit" value="Создать">
            </label>
        </a>

        @if(page.getCourses().isEmpty())
        <p>Пока не добавлено ни одного курса</p>
        @else
        @for(var course : page.getCourses())
        <div>
            <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2>
            <p>${course.getDescription()}</p>
        </div>
        @endfor
        @endif
    `,
    footer = @`<p>My GitHub: <a href="https://github.com/artmper">https://github.com/artmper</a></p>
    `
)

