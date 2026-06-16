@import org.example.hexlet.dto.courses.CoursesPage
@import org.example.hexlet.dto.courses.CoursePage

@param CoursePage coursePage
@param CoursesPage page

@template.layout.page(
    page = coursePage,
    content = @`<h1>${page.getHeader()}</h1>
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
    footer = @`<p>My GitHub: https://github.com/artmper</p>
    `
)

