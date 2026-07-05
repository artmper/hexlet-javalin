@import gg.jte.Content
@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.BasePage

@param String title
@param String description
@param Content content
@param Content footer
@param Content flash = null
@param BasePage page = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        @if(description != null)
            <meta name="description" content="${description}">
        @endif
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
              crossorigin="anonymous">
        <title>${title}</title>
    </head>
    <body>
        <p><a href="${NamedRoutes.rootPath()}">На Главную</a></p>
        <h1>${title}</h1>
        <div class="content">
            @if(page != null && page.getFlash() != null)
                <p>${flash}</p>
            @endif
            ${content}
        </div>
        @if (footer != null)
            <div class="footer">
                ${footer}
            </div>
        @endif
    </body>
</html>