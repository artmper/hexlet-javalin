package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.BasePage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,4,4,4,15,15,15,16,16,16,16,16,16,16,16,16,17,17,23,23,23,26,26,26,26,26,26,26,26,26,27,27,27,29,29,30,30,30,31,31,32,32,32,34,34,36,36,36,38,38,40,40,40,4,5,6,7,8,9,9,9,9};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, String description, Content content, Content footer, Content flash, BasePage page) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n    <head>\n        <meta charset=\"utf-8\" />\n        ");
		if (description != null) {
			jteOutput.writeContent("\n            <meta name=\"description\"");
			var __jte_html_attribute_0 = description;
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
				jteOutput.writeContent(" content=\"");
				jteOutput.setContext("meta", "content");
				jteOutput.writeUserContent(__jte_html_attribute_0);
				jteOutput.setContext("meta", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent(">\n        ");
		}
		jteOutput.writeContent("\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css\"\n              rel=\"stylesheet\"\n              integrity=\"sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB\"\n              crossorigin=\"anonymous\">\n        <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(title);
		jteOutput.writeContent("</title>\n    </head>\n    <body>\n        <p><a");
		var __jte_html_attribute_1 = NamedRoutes.rootPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">На Главную</a></p>\n        <h1>");
		jteOutput.setContext("h1", null);
		jteOutput.writeUserContent(title);
		jteOutput.writeContent("</h1>\n        <div class=\"content\">\n            ");
		if (page != null && page.getFlash() != null) {
			jteOutput.writeContent("\n                <p>");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(flash);
			jteOutput.writeContent("</p>\n            ");
		}
		jteOutput.writeContent("\n            ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n        </div>\n        ");
		if (footer != null) {
			jteOutput.writeContent("\n            <div class=\"footer\">\n                ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(footer);
			jteOutput.writeContent("\n            </div>\n        ");
		}
		jteOutput.writeContent("\n    </body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.get("title");
		String description = (String)params.get("description");
		Content content = (Content)params.get("content");
		Content footer = (Content)params.get("footer");
		Content flash = (Content)params.getOrDefault("flash", null);
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, title, description, content, footer, flash, page);
	}
}
