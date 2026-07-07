package gg.jte.generated.ondemand;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.MainPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,14,14,14,15,15,15,19,19,21,21,21,21,21,21,21,21,21,23,23,25,25,27,27,27,27,27,27,27,27,27,28,28,28,28,28,28,28,28,28,32,32,32,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, MainPage page) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n    <head>\n        <meta charset=\"utf-8\" />\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n        <title>Hello Hexlet!</title>\n    </head>\n    <body>\n        <main>\n            <h1>Привет, Хекслет!</h1>\n            ");
		if (page.getCurrentUser() != null) {
			jteOutput.writeContent("\n                <p>Добро пожаловать, ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(page.getCurrentUser());
			jteOutput.writeContent(".</p>\n                <form action=\"/session/destroy\" method=\"post\">\n                    <input type=\"submit\" value=\"Выход\">\n                </form>\n            ");
		}
		jteOutput.writeContent("\n            <div style=\"display: flex; justify-content: center;\">\n                <a");
		var __jte_html_attribute_0 = NamedRoutes.buildSessionPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Логин</a>\n            </div>\n            ");
		if (!page.isVisited()) {
			jteOutput.writeContent("\n                <h2>Тестирую куки...</h2>\n            ");
		}
		jteOutput.writeContent("\n            <ul>\n                <li><a");
		var __jte_html_attribute_1 = NamedRoutes.coursesPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Courses</a></li>\n                <li><a");
		var __jte_html_attribute_2 = NamedRoutes.usersPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Users</a></li>\n            </ul>\n        </main>\n    </body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		MainPage page = (MainPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
