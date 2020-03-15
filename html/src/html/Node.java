package html;

import java.util.ArrayList;
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
		 * 		  | parent == null || parents.children.stream.anyMatch(c -> c == this) //DIT IS EEN AANPASSING iets.stream
		 * @invar For each child of this node, the child's parent equals this node.
		 * 		  | children.stream.allMatch(c -> c != null && c.parent == this)
		 * 
		 */
	private String tag;
	private String text;
	private Node parent;
	private ArrayList<Node> children;
	
	public String getTag() {return tag; }
	public String getText() {return text;}
	
	public Node getParent() {return parent; }
	// peer object 
	
	public Node[] getChildren() { 
		// return children; //FOUT: REPRESENTATION EXPOSURE kan dan bv nulpointers erin steken, willen we vermijden. 
		// we willen dat one code goed werkt zelfs als de klantcode niet tegoei werkt, interne objecten niet doorgeven aan de klant
		// representation object
		return children.toArray(new Node[0]); // new Node[0] meegeven om Array van Node te krijgen, anders lukt dat niet
	}
	
	
	public Node(String tag, String text) {
		this.tag = tag;
		this.text = text;
		this.children = new ArrayList<Node>();
}
	
	public void addChild(Node child) {
		children.add(child);
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
		children.remove(child);
		child.parent = null;
	}
	

	// enhanced for loop blijft werken, lus iets anders
	public String toString() {
		if (text != null)
			return text;
		String result = "<" + tag + ">";
		for (Node child: children)
		// for (int i = 0; i < children.size(); i++) {
			// Node child = children.get(i);
			result += child.toString();
		result += "</" + tag + ">";
		return result;
	}
}