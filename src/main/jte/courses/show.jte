@import org.example.hexlet.dto.courses.CoursePage

@param CoursePage coursePage

@template.layout.page(
    page = coursePage,
    content = @`
        <h1>${coursePage.getCourse().getName()}</h1>
        <p>${coursePage.getCourse().getDescription()}</p>
    `,
    footer = @`<p>My GitHub: https://github.com/artmper</p>
    `
)

