package html;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HtmlTest {

	@Test
	void test() {
		Node html = new Node("html", null);
		Node head = new Node("head", null);
		html.addChild(head);
		Node title = new Node("title", null);
		head.addChild(title);
		Node titleText = new Node(null , "JLearner");
		title.addChild(titleText);
		Node script = new Node("script", null);
		head.addChild(script);
		Node scriptText = new Node(null, "alert('Hello world!')");
		script.addChild(scriptText);
		Node nonesense = new Node("nonesense", null);
//		head.addChild(nonesense);
//		head.removeChild(nonesense);
		
		assertEquals(
				"<html>" +
				"<head>" +
				"<title>JLearner</title>" +
				"<script>alert('Hello world!')</script>" +
				"</head>" +
				"</html>",
				html.toString());
	}

}
