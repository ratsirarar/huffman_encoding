package utils;

public class HuffmanEncoding {
	
	//To be improved: This is used to store char and its 
	//corresponding code using char value.
	//Large number of cells are not used.
	private String[] codeTable = new String[91];
	private Node [] leaf;
	private Node encodedTree;
	private int counter = 0;
	
	public HuffmanEncoding(Node[] freqTable) {
		this.buildEncodingTable(freqTable);
	}

	public String encode (String str) throws InvalidInputException {
		str = str.toUpperCase();
		String encoded_msg = "";
		for (int i=0; i<str.length(); i++){
			char currentChar = str.charAt(i);
			if (Character.isLetter(currentChar)){
				encoded_msg += this.codeTable[currentChar];
			}
		}
		return encoded_msg;
	}
	
	public String decode (String str){
		String decoded_msg = "";
		Node tree = this.encodedTree;
		for (int i=0; i<str.length(); i++){
			
			if (str.charAt(i) == '0'){
				tree = tree.getLeft();
			}
			else if (str.charAt(i) == '1'){
				tree = tree.getRight();
			}
			if (tree.isLeaf()){
				decoded_msg += tree.getSymbol();
				tree = this.encodedTree;
			}	
		}
		return decoded_msg;
	}
	
	private String getSymbolFromCode(Node node, String str ){
		if (node.isLeaf()){
			return node.getSymbol();
		}
		if (str == "0"){
			getSymbolFromCode(node.getLeft(), str);
		}
		else if (str == "0"){
			getSymbolFromCode(node.getRight(), str);
		}
		return null;
	}
	
	private void buildEncodingTable(Node[] listNode){
		PriorityQueue pq = new PriorityQueue(listNode.length);
		for (int i=0; i<listNode.length; i++){
			pq.insert(listNode[i]);
		}
		
		while (pq.length() > 1){
			Node d1 = pq.dequeue();
			Node d2 = pq.dequeue();
			Node parent_node = d1.add(d2);
			parent_node.setLeft(d1);
			parent_node.setRight(d2);
			pq.insert(parent_node);
		}
		
		this.setEncodedTree(pq.dequeue());
		this.leaf = new Node[listNode.length];
		this.buildCode(this.getEncodedTree(), "");
		
	}
	
	public String getTree(String order){
		String msg = "";
		if (order == "preOrder"){
			 msg = this.preOrder(this.encodedTree, "");
		}
		return msg;
	}
	
	public String getTableCode(){
		String msg = "";
		for (int i=0; i<counter; i++){
			msg += this.leaf[i].getSymbol() + ":"
					+ this.codeTable[this.leaf[i].getSymbol().charAt(0)] + "; \n";
				   	
		}
		return msg;
	}
	
	private String preOrder(Node tree, String msg) {
		if (tree == null){
			return msg ;
		}
	
		msg += tree.getSymbol() + ":"
			   + tree.getFrequency() + ";\n";
		
		msg = this.preOrder(tree.getLeft(), msg);
		msg = this.preOrder(tree.getRight(), msg);
		return msg;
	}
	
	private void buildCode(Node node, String code){
		if (!node.isLeaf()){
			this.buildCode(node.getLeft(), code+"0");
			this.buildCode(node.getRight(), code+"1");
		}
		else {
			int symbolChar = node.getSymbol().charAt(0);
			codeTable[symbolChar] = code;
			this.leaf[this.counter++] = node;
		}
	}
	
	public Node getEncodedTree() {
		return encodedTree;
	}

	public void setEncodedTree(Node encodedTree) {
		this.encodedTree = encodedTree;
	}
	
}
