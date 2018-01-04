import java.util.*;
import java.io.*;
public class HuffmanCode {

	public static void main(String args[]){
		String fileName = "D:\\Studies\\IUB\\Sem1\\Algo\\assignment4\\123.txt";
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();          
		StringBuffer fileStr = new StringBuffer();
		String s = new String();
		try {
			try(InputStream inputStream = new FileInputStream(fileName);
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);			// reading file as an input
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
				String line;
				while ((line = bufferedReader.readLine()) != null){
					fileStr.append(line);
				}
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Exception caught :: " + e);
		}
		s = fileStr.toString();
		for(int i = 97; i<= 122 ; i++) {
			map.put((char) i, 0); 																	 	// initializing map for all 32 characters with 0 as value
		}
		map.put('?', 0);
		map.put('\'', 0);
		map.put(' ', 0);
		map.put('!', 0);
		map.put('.', 0);
		map.put(',', 0);
		s = s.toLowerCase();
		for(int i = 0; i < s.length(); i++){															// counting the character frequency
			char c = s.charAt(i);
			if((c <= 'z' && c >= 'a') || (c== '?' || c == '.' || c == '!' || c == '\'' || c == ' ' || c == ',')) {
				Integer val = map.get(new Character(c));
				if(val != null){
					map.put(c, new Integer(val + 1));
				}else{
					map.put(c,1);
				}
			}
		}
		HNode root = Huffman(map); 																	 	//calling function to generate Huffman Tree
		HashMap<Character, String> codeMap = new HashMap<Character, String>();
		codeMap = call_node(root, codeMap, root.getHuffCode());											// traversing the tree and saving character and huffman code in a map
		System.out.println("Total number of characters :: " + s.length());
		System.out.print("Total size of file with fixed length encoding of 5 bits :: ");
		long f = 0;
		for(Map.Entry<Character, Integer> entry : map.entrySet()) {
			f += entry.getValue()*5;
		}
		System.out.println(f);
		System.out.println();
		System.out.println("Output Huffman Code : ");
		System.out.println("CHARACTER \t" + "FREQUENCY \t" + " HUFFMAN CODE\t");
		for(Map.Entry<Character, String> entry : codeMap.entrySet()) {
			System.out.println(  entry.getKey() +  "\t\t" + map.get(entry.getKey()) +  "\t\t "+ entry.getValue());		
		}
		System.out.println();
		System.out.print("Total Size of file using Huffman code :: ");
		long sum = 0;
		for(Map.Entry<Character, String> entry : codeMap.entrySet()) {
			sum += map.get(entry.getKey())*entry.getValue().length(); 
		}
		System.out.println(sum);
		System.out.println("Difference in size of file :: " + (f-sum));
		System.out.println("Percentage compressed :: " + (double)(f-sum)/f*100);
	}

	// function to generate Huffman Tree
	private static HNode Huffman(HashMap<Character,Integer> map) {
		PriorityQueue<HNode> q = new PriorityQueue<>(convertMapToList(map));
		for(int i = 1; i < map.size(); i++) {															
			HNode z = new HNode();
			HNode x = q.poll();
			z.setLeft(x);
			HNode y = q.poll();
			z.setRight(y);
			z.setFreq(x.getFreq()+y.getFreq());
			z.setCharacter('^');
			q.add(z);
		}
		return q.poll();
	}

	// Function to Convert map of character and Integer to List of nodes
	private static ArrayList<HNode> convertMapToList(Map<Character, Integer> map) {
		ArrayList<HNode> list = new ArrayList<>();														
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			list.add(new HNode(entry.getKey(), entry.getValue()));
		}
		return list;
	}

	// function to traverse the tree and note the huffman code of each character
	private static HashMap<Character, String> call_node(HNode node, HashMap<Character, String> codeMap, String hCode){
		if(null != node.getLeft()) {																	
			String code = "";	
			code = code + hCode + "0";
			node.getLeft().setHuffCode(code);
			call_node(node.getLeft(), codeMap, code);
		}
		if(null != node.getRight()) {
			String code = "";
			code = code + hCode + "1";
			node.getRight().setHuffCode(code);
			call_node(node.getRight(), codeMap, code);
		}
		if('^' != node.getCharacter()) {
			codeMap.put(node.getCharacter(), node.getHuffCode());
		}
		return codeMap;
	}
}