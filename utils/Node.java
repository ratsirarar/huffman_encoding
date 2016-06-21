package utils;

public class Node implements Comparable<Node> {
	private String symbol;
	private int frequency;
	private Node left;
	private Node right;
	
	public Node (String symbol, int freq){
		this.symbol = symbol;
		this.frequency = freq;
	    this.left = null;
	    this.right = null;
	}
	
	@Override
	public int compareTo(Node o) {
		if (this.frequency > o.getFrequency()){
			return 1;
		}
		if (this.frequency == o.getFrequency()){
			if (this.symbol.length() > o.getSymbol().length()){
				return 1;
			}
			else if(this.symbol.length() == o.getSymbol().length()){
				if (this.symbol.compareTo(o.getSymbol()) > 1 ){
					return 1;
				}
				else{
					return 0;
				}
			}
			else {
				return 0;
			}
		}		
		return 0;
	}
	
	public Node add(Node o){
		return new Node(this.symbol + o.getSymbol(),
				    this.frequency +  o.getFrequency());
	}
	
	public boolean isLeaf(){
		return (this.left == null && this.right == null)?true:false;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
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
	
}
