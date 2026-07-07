@import org.example.hexlet.dto.courses.CoursePage

@param CoursePage page

@template.layout.page(
    title = page.getCourse().getName(),
    description = page.getCourse().getDescription(),
    content = @`
        <p>${page.getCourse().getDescription()}</p>
    `,
    footer = null
)

