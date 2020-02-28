package html;

/**
 * Version with only FirstChild implementation (not LastChild)
 * @author thiba
 *
 */
public class Node {
	private String tag;
	private String text;
	private Node parent;
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
		child.parent = this;
	}
	
	public void remove() {
		if (parent != null)
			parent.removeChild(this);
	}
//	public void removeChild() {
//		if (previousSibling == null) {
//			parent.firstChild = nextSibling;
//			if (parent.firstChild == null)
//				parent.lastChild = null;
//		} else {
//			if (nextSibling == null) {
//				parent.lastChild = previousSibling;
//			} else {
//				nextSibling.previousSibling = previousSibling;
//			}
//			previousSibling.nextSibling = nextSibling;
//		}
//		nextSibling = null;
//		previousSibling = null;
//		parent = null;
//			
//	}
	
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
		child.parent = null;
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
