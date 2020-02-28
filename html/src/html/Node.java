package html;

/**
 * Version with only FirstChild implementation (not LastChild)
 * @author thiba
 *
 */
public class Node {
	private String tag;
	private String text;
	private Node firstChild;
	private Node lastChild;
	private Node nextSibling;
	private Node previousSibling;
	
	public Node(String tag, String text) {
		this.tag = tag;
		this.text = text;
}
	public void addChild(Node child) {
		if (firstChild != null) {
			lastChild.nextSibling = child;
			child.previousSibling = lastChild;
			lastChild = child;
		}
		else {
			firstChild = child;
			lastChild = child;
		}
	}
	
	public void removeChild(Node child) {
		if (child == firstChild) { 
			firstChild = firstChild.nextSibling;
			if (firstChild == null)
				lastChild = null;
		} else {
			if (child.nextSibling == null) {
				lastChild = child.previousSibling;
			} else {
				child.nextSibling.previousSibling = child.previousSibling;
			}
			child.previousSibling.nextSibling = child.nextSibling;
		}
		child.nextSibling = null;
		child.previousSibling = null;
	}
	


	public String toString() {
		if (text != null)
			return text;
		String result = "<" + tag + ">";
		Node child = firstChild;
		while (child != null) {
			result += child.toString();
			child = child.nextSibling;
		}
		result += "</" + tag + ">";
		return result;
	}
}
