package logic;

public class Node {
	
	private Node left=null;
	private Node right=null;
	private int weight=-1;
	private Node parent=null;
	
	
	public Node(int weight) {
		this.weight=weight;
	}
	
	
	public Node() {
		
	}
	

	public Node getParent() {
		return parent;
	}


	public void setParent(Node parent) {
		this.parent = parent;
	}


	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	
	
	
	
	
}
