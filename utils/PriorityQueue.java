package utils;

public class PriorityQueue {
	private Node[] arrayNode;
	private int size;
	
	public PriorityQueue(int size) {
		this.arrayNode = new Node[size]; 
		this.size = -1;
	}
	
	public void insert(Node data){
		this.arrayNode[++this.size] = data;
		this.heapifyUp(this.size);
	}
	
	public Node dequeue(){
		Node elmt = null;
		if (this.length() == 1){
			elmt = this.arrayNode[this.size--];
		}
		else if (this.length() > 1){
			this.swap(0, this.size);
			elmt = this.arrayNode[this.size--];
			this.hepifyDown(0);
		}
		return elmt;
	}
	
	private void hepifyDown(int k) {
		int key = this.leftKey(k);
		while (key < this.length()){
			if (key+1<this.length() 
			&& this.arrayNode[key].compareTo(this.arrayNode[key+1]) == 1){
				key++;
			}
			if (!this.greater(k, key)) break;
			this.swap(k, key);
			k = key;
			key = this.leftKey(k);	
		}
	}

	private int leftKey(int k) {
		return 2*k + 1;
	}

	private void heapifyUp(int k) {
		int parentKey = this.parentKey(k);
		while(parentKey>=0 && this.greater(parentKey, k)){
			this.swap(k, parentKey);
			k = parentKey;
			parentKey = this.parentKey(k);
		}
	}
	
	private void swap(int k1, int k2) {
		Node tmp = this.arrayNode[k1];
		this.arrayNode[k1] = this.arrayNode[k2];
		this.arrayNode[k2] = tmp;
	}

	private boolean greater(int k1, int k2) {
		
		return (this.arrayNode[k1].compareTo(this.arrayNode[k2]) ==1)
				?true:false;
	}

	private int parentKey(int k) {
		return (k%2 == 0)?(k-2)/2:(k-1)/2;
	}

	public int length(){
		return this.size + 1;
	}
	
	public Node[] getArrayNode() {
		return arrayNode;
	}
	
	public void setArrayNode(Node[] arrayNode) {
		this.arrayNode = arrayNode;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
}

