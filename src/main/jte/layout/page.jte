@import gg.jte.Content
@import org.example.hexlet.dto.courses.CoursePage

@param CoursePage page
@param Content content
@param Content footer

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        @if(page.getCourse().getDescription() != null)
            <meta name="description" content="${page.getCourse().getDescription()}">
        @endif
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>${page.getCourse().getName()}</title>
    </head>
    <body>
        <h1>${page.getCourse().getName()}</h1>
        <div class="content">
            ${content}
        </div>
        @if (footer != null)
            <div class="footer">
                ${footer}
            </div>
        @endif
    </body>
</html>