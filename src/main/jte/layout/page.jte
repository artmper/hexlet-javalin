@import gg.jte.Content

@param String title
@param String description
@param Content content
@param Content footer

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        @if(description != null)
            <meta name="description" content="${description}">
        @endif
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>${title}</title>
    </head>
    <body>
        <p><a href="/">На Главную</a></p>
        <h1>${title}</h1>
        <div class="content">
            ${content}
        </div>
        @if (footer != null)
            <div class="footer" style="margin-top: 30px; border-top: 2px solid #ccc; padding-top: 15px; text-align: center;">
                ${footer}
            </div>
        @endif
    </body>
</html>