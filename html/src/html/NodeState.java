package html;

import java.util.List;

public class NodeState {
	private final String text;
	private final String tag;
	private final Node parent;
	private final List<Node> children;
	
	public NodeState(String text, String tag, Node parent, List<Node> children) {
		super();
		this.text = text;
		this.tag = tag;
		this.parent = parent;
		this.children = children;
	}

	public String getText() {
		return text;
	}

	public String getTag() {
		return tag;
	}

	public Node getParent() {
		return parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeState other = (NodeState) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	
	
	
}
