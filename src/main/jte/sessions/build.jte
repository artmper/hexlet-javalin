@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.BasePage
@param BasePage page

@template.layout.page(
    footer = null,
    page = page,
    content = @`
    <form action="${NamedRoutes.sessionsPath()}" method="post">
        <input type="text" placeholder="Nickname" name="nickname" />
        <input type="password" placeholder="Password" name="password" />
        <input type="submit" />
    </form>
    `,
    flash = @`
    <div class="alert alert-danger" role="alert">
        ${page.getFlash()}
    </div>
    `,
    title = null,
    description = null
)
