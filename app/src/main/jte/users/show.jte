@import org.example.hexlet.dto.users.UserPage

@param UserPage page

@template.layout.page(
title = page.getUser().getName(),
description = page.getUser().getEmail(),
content = @`
    <p>${page.getUser().getEmail()}</p>
`,
footer = null
)
