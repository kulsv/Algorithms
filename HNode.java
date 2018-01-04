
public class HNode implements Comparable<HNode> {

	private char character;
	private int freq;
	private HNode left;
	private HNode right;
	private String huffCode = "";

	public HNode(char c, int freq) {
		super();
		this.character = c;
		this.freq = freq;
	}
	public HNode() {
		super();
	}
	public char getCharacter() {
		return character;
	}
	public void setCharacter(char c) {
		this.character = c;
	}
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public HNode getLeft() {
		return left;
	}
	public void setLeft(HNode left) {
		this.left = left;
	}
	public HNode getRight() {
		return right;
	}
	public void setRight(HNode right) {
		this.right = right;
	}

	public String getHuffCode() {
		return huffCode;
	}
	public void setHuffCode(String huffCode) {
		this.huffCode = huffCode;
	}
	@Override
	public int compareTo(HNode n) {								//overriding comparable.compareTo to compare nodes based on character frequencies
		if(this.freq < n.freq){
			return -1;
		}else if(this.freq > n.freq){
			return 1;
		}else
			return 0;
	}



}
