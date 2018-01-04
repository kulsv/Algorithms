public class BST {

	public static void main(String args[]) {
		BSTNode T = null;
		int sum = 0;
		int arr[] = {5, 3, 9, 7, 1, 4, 0, 12, 11, 13, 15, 6, 2, 8, 10, 14};

		for(int i = 0; i < arr.length; i++) {
			BSTNode z = new BSTNode();
			z.setKey(arr[i]);
			T = insert(T, z);
		}
		System.out.println("Tree root key :: " + T.getKey());
		boolean b = contains(T, 119);
		System.out.println("Tree contains 21 :: " + b);
		boolean c = contains(T, 9);
		System.out.println("Tree contains 9 :: " + c);
		int sz = size(T);
		System.out.println("Tree size :: " + sz);
		System.out.println("Printing the tree :: ");
		print(T);
		System.out.println("Smallest element :: " + smallest(T));
		System.out.println("Largest element :: " + largest(T));
		greaterSumTree(T, sum);
		System.out.println("Printing the modified tree ::");
		print(T);
	}

	public static BSTNode insert(BSTNode T, BSTNode z) {
		BSTNode x = new BSTNode();
		BSTNode y = new BSTNode();
		y = null;
		x = T;
		while(x != null) {
			y = x;
			if(z.getKey() < x.getKey()) {
				x = x.getLeft();
			}else 
				x = x.getRight();
		}
		if(y == null) {
			T = new BSTNode();
			T = z;
		}else if(z.getKey() < y.getKey())
			y.setLeft(z);
		else 
			y.setRight(z);
		return T;
	}

	public static boolean contains(BSTNode T, int z) {
		if(T == null)
			return false;
		if(T.getKey() == z)
			return true;
		if(z < T.getKey())
			return contains(T.getLeft(), z);
		else 
			return contains(T.getRight(), z);
	}

	public static int size(BSTNode T) {
		int left_count=0;
		int right_count=0;
		if(null == T)   
			return 0;         
		left_count=size(T.getLeft()); 
		right_count=size(T.getRight());     
		return left_count+right_count+1; 
	}

	public static void print(BSTNode T) {
		if(null != T) {
			print(T.getLeft());
			System.out.println(T.getKey() + " ");
			print(T.getRight());
		}
	}

	public static int smallest(BSTNode T) {
		while(null != T.getLeft())
			T = T.getLeft();
		return T.getKey();
	}

	public static int largest(BSTNode T) {
		while(null != T.getRight())
			T = T.getRight();
		return T.getKey();
	}

	public static int greaterSumTree(BSTNode Tn, int sum) {
		if(Tn == null)
			return sum;
		sum = greaterSumTree(Tn.getRight(), sum);
		sum = sum + Tn.getKey();
		Tn.setKey(sum - Tn.getKey());
		sum = greaterSumTree(Tn.getLeft(), sum);
		return sum;
	}
}