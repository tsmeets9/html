package html;

import java.util.Arrays;

/**
 * DIT ZIJN INVARIANTEN DIE DE GEBRUIKER KAN ZIEN, die de client kan zien
 * Version with only FirstChild implementation (not LastChild)
 * @author thiba
 * @invar | (getTag() == null) == (getText() != null 
 * @invar | getParent() == null || Arrays.stream(getParents().getChildren()).anyMatch(c -> c == this)
 * @invar | Arrays.stream(getChildren()).anyMatch(c -> c!= null && c.getParent() == this)
 *
 */
public class Node {
	
	// INTERNE DOCUMENTATIE, PER METHODE KAN JE CHECKEN OF DIT ZO IS. ALS ZE IN HET BEGIN VAN EEN METHODE GELDEN, GELDEN ZE DAN OOK OP HET EINDE
	
		/* ALtijd goed definiëren wat de geldige toestanden zijn
		 * @invar Either {@code tag} or {@code text} must be non-null
		 * 		|tag != null || text != null
		 * Either {@code tag} or {@code text} must be null
		 * 		|tag == null || text == null
		 * 
		 * zelfde als vorige twee beknopter geschreven:
		 * @invar | (tag == null) == (text != null)
		 * @invar |children != null    //kan je kiezen of je dit wilt, we moeten kiezen hoe we dan een lege lijst kinderen maken 
		 * @invar |parent == null 
		 * head is kind van html dus met html ook parent zijn van head
		 * 
		 * @invar If this node has a parent, this node is among it's parent's children.
		 * 		  | parent == null || Arrays.stream(parents.children).anyMatch(c -> c == this)
		 * @invar For each child of this node, the child's parent equals this node.
		 * 		  | Arrays.stream(children).allMatch(c -> c != null && c.parent == this)
		 * 
		 */
	private String tag;
	private String text;
	private Node parent;
	private Node[] children;
	
	public String getTag() {return tag; }
	public String getText() {return text;}
	
	public Node getParent() {return parent; }
	// peer object 
	
	public Node[] getChildren() { 
		// return children; //FOUT: REPRESENTATION EXPOSURE kan dan bv nulpointers erin steken, willen we vermijden. 
		// we willen dat one code goed werkt zelfs als de klantcode niet tegoei werkt, interne objecten niet doorgeven aan de klant
		// representation object
		return Arrays.copyOf(children, children.length);
	}
	
	
	public Node(String tag, String text) {
		this.tag = tag;
		this.text = text;
		this.children = new Node[0];
}
	
	public void addChild(Node child) {
		Node[] newChildren = new Node[children.length + 1];
		System.arraycopy(children, 0, newChildren, 0, children.length);
		newChildren[children.length] = child;
		children = newChildren;
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
		Node[] newChildren = new Node[children.length -1];
		int index = 0;
		while (children[index] != child)
			index++;
		System.arraycopy(children, 0, newChildren, 0, index);
		System.arraycopy(children, index + 1, newChildren, index, children.length - index - 1);
		children = newChildren;
		child.parent = null;
	}
	


	public String toString() {
		if (text != null)
			return text;
		String result = "<" + tag + ">";
		for (Node child: children)
		// for (int i = 0; i < children.length; i++) {
			// Node child = children[i]
			result += child.toString();
		result += "</" + tag + ">";
		return result;
	}
}