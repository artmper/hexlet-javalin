package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import org.example.hexlet.dto.courses.CoursePage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,12,12,12,13,13,13,13,13,13,13,13,13,14,14,16,16,16,19,19,19,21,21,21,23,23,25,25,25,27,27,29,29,29,3,4,5,6,6,6,6};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, String description, Content content, Content footer) {
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
		jteOutput.writeContent("\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n        <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(title);
		jteOutput.writeContent("</title>\n    </head>\n    <body>\n        <h1>");
		jteOutput.setContext("h1", null);
		jteOutput.writeUserContent(title);
		jteOutput.writeContent("</h1>\n        <div class=\"content\">\n            ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n        </div>\n        ");
		if (footer != null) {
			jteOutput.writeContent("\n            <div class=\"footer\" style=\"margin-top: 30px; border-top: 2px solid #ccc; padding-top: 15px; text-align: center;\">\n                ");
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
		render(jteOutput, jteHtmlInterceptor, title, description, content, footer);
	}
}
