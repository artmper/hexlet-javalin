@import org.example.hexlet.dto.courses.BuildCoursePage
@import org.example.hexlet.NamedRoutes
@param BuildCoursePage page

@template.layout.page(
    title = "Создание курса",
    description = "Форма создания нового курса",
    content = @`
        @if (page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action="${NamedRoutes.coursesPath()}" method="post">
            <div>
                <label>
                    Название курса
                    <input type="text" required name="name"/>
                </label>
            </div>
            <div>
                <label>
                    Описание
                    <input type="text" required name="description"/>
                </label>
            </div>
            <input type="submit" value="Создать"/>
        </form>
        `,
        footer = null)
