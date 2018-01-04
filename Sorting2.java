import java.util.*;
import java.io.*;
class Sorting2{	
	int x = 0;
	int randomArray[];

	public static void insert(int a[])
	{
		int x,j;
		for(int i=1;i<a.length;i++)
		{
			x=a[i]; j=i;
			while(j>0&&a[j-1]>x)
			{
				a[j]=a[j-1];
				j=j-1;
			}
			a[j]=x;
		}
	}
	
	static void m_sort(int A[], int p, int r)
	{
		int q;
		if (r > p)
		{
			q = (r + p) / 2;
			m_sort(A, p, q);
			m_sort(A, q+1, r);
			merge(A, p, q, r);
		}
	}
	
	static void merge(int A[], int p, int q, int r)
	{
		int n1 = q-p+1;
		int n2 = r-q;
		int L[] = new int[n1+1];
		int R[] = new int[n2+1];
		int i=0;
		int j=0;
		for(i=0; i<n1; i++)
			L[i] = A[p+i-1];
		for(j=0; j<n2; j++)
			R[j] = A[q+j];
		
		L[n1] = 655355;
		R[n2] = 655355;
		i = 0;
		j = 0;
		
		for(int k=p; k<r; k++) {
			if(L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			}else {
				A[k] = R[j];
				j++;
			}
		}
	}

	public static int partition(int A[],int p, int r)
	{
		
		int x = A[r];
		int i = p-1;
		int temp=0;
		for(int j=p; j<r-1; j++) {
			if(A[j] <= x) {
				i++;
				temp=A[i];
				A[i]=A[j];
				A[j]=temp;
			}
		}
		temp = 0;
		temp = A[i+1];
		A[i+1] = A[r];
		A[r] = temp;
		return ++i;
	}

	static void quick(int a[],int left, int right)
	{
		int p;
		if(left<right)
		{
			p=partition(a,left,right);
			quick(a,left,p-1);
			quick(a,p+1,right);
		}
	}

	public static void main(String args[]){
		Sorting2 s = new Sorting2();
		String fileName = "";
			HashMap<Integer, HashMap<Long, Long>> insert_plot1 = new HashMap<Integer, HashMap<Long, Long>>();
			HashMap<Integer, HashMap<Long, Long>>  merge_plot1 = new HashMap<Integer, HashMap<Long, Long>>();
			HashMap<Integer, HashMap<Long, Long>> quick_plot1 = new HashMap<Integer, HashMap<Long, Long>>();
			for(int option = 1; option <=3; option++){
				HashMap<Long, Long> plot1 = new HashMap<Long, Long>();
				HashMap<Long, Long> plot2 = new HashMap<Long, Long>();
				HashMap<Long, Long> plot3 = new HashMap<Long, Long>();
				for(int n = 500; n<=10000; n=n+500){
				//for(int n=5;n<20; n+=5) {
					int i = 0;
					long timeArray_insert[] = new long[10];
					long timeArray_merge[] = new long[10];
					long timeArray_quick[] = new long[10];
					long avg_insert = 0;
					long avg_merge = 0;
					long avg_quick = 0;
					//long timeArray[] = new long[10];
					if(option == 1){																// Plot 1 handling
						
						while(i<10){
							int ints[]=new int[n];
							fileName = "D:\\Studies\\Algorithm\\sort_files\\plot1_";
							fileName = fileName + "_1_"+n+"_"+i+".txt";
							File file1 = new File(fileName);
							s.generateNumbers(n, option, file1);
							Scanner scanner;
							try {
								scanner = new Scanner(new File(fileName));
								int idx = 0;
								while(scanner.hasNext()){
									String nxt = scanner.next();
									ints[idx] = Integer.parseInt(nxt);
									idx++;
								}
								scanner.close();
							} catch (Exception e) {
							}
							long timeTaken1 = getExecTime(ints, 1);  		// calling time calculating function for insertion sort
							long timeTaken2 = getExecTime(ints , 2);			// calling time calculating function for merge sort
							long timeTaken3 = getExecTime(ints, 3);			// calling time calculating function for quick sort
							timeArray_insert[i] = timeTaken1;
							timeArray_merge[i] = timeTaken2;
							timeArray_quick[i] = timeTaken3;
							i++;
						}
						avg_insert = avgTime(timeArray_insert);
						avg_merge = avgTime(timeArray_merge);
						avg_quick = avgTime(timeArray_quick);
					}else if(option == 2 || option == 3){											// Plot 2 and Plot 3 handling
						if(option == 2) {
							fileName = "D:\\Studies\\Algorithm\\sort_files\\plot2_";
							fileName = fileName + "_"+n+"_"+".txt";
							File file2 = new File(fileName);
							s.generateNumbers(n, option, file2);
						}else if(option == 3) {
							fileName = "D:\\Studies\\Algorithm\\sort_files\\plot3_";
							fileName = fileName + "_"+n+"_"+".txt";
							File file3 = new File(fileName);
							s.generateNumbers(n, option, file3);
						}
						int ints[]=new int[n];
						Scanner scanner;
						try {
							scanner = new Scanner(new File(fileName));
							int idx = 0;
							while(scanner.hasNext()){
								String nxt = scanner.next();
								ints[idx] = Integer.parseInt(nxt);
								idx++;
							}
							scanner.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						avg_insert = getExecTime(ints, 1);
						avg_merge = getExecTime(ints, 2);
						avg_quick = getExecTime(ints, 3);
					}
					
					
					plot1.put(new Long(n),new Long(avg_insert));
					insert_plot1.put(new Integer(option), plot1);
					plot2.put(new Long(n),new Long(avg_merge));
					merge_plot1.put(new Integer(option), plot2);
					plot3.put(new Long(n),new Long(avg_quick));
					quick_plot1.put(new Integer(option), plot3);
			
				}
					
		}
			
			/*System.out.println("Insert Map :: " + insert_plot1 + " ns");  
			System.out.println("Merge Map :: " + merge_plot1 + " ns"); 
			System.out.println("QUICK Map :: " + quick_plot1 + " ns");*/
			System.out.println("insertion sort------------------------------------------------------------------------------------------ : ");
			for(Map.Entry<Integer, HashMap<Long, Long>> hMap : insert_plot1.entrySet()){
				System.out.println("insert main key :: ------------------ : " + hMap.getKey());
				System.out.println("insert key :: ------------------ : " + hMap.getKey());
				HashMap<Long, Long> map = hMap.getValue();
				for (Map.Entry<Long, Long> entry : map.entrySet()) {
					System.out.println(entry.getKey());
				}
				
				System.out.println("insert value-------------------- : ");
				
				for (Map.Entry<Long, Long> entry : map.entrySet()) {
					long value = entry.getValue();
					double milliseconds = value/1000000.0;
				 System.out.println(milliseconds);
				}
			}
			
			System.out.println("merge sort----------------------------------------------------------------------------------------------- : ");
			for(Map.Entry<Integer, HashMap<Long, Long>> hMap : merge_plot1.entrySet()){
				System.out.println("merge main key :: ------------------ : " + hMap.getKey());
				
				System.out.println("merge key :: ------------------ : " + hMap.getKey());
				HashMap<Long, Long> map = hMap.getValue();
				for (Map.Entry<Long, Long> entry : map.entrySet()) {
					System.out.println(entry.getKey());
				}
				
				System.out.println("merge value------------------ : ");
				
				for (Map.Entry<Long, Long> entry : map.entrySet()) {
					long value = entry.getValue();
					double milliseconds = value/1000000.0;
				 System.out.println(milliseconds);
				}
			}
			
			System.out.println("quick sort------------------------------------------------------------------------------------------ : ");
			for(Map.Entry<Integer, HashMap<Long, Long>> hMap : quick_plot1.entrySet()){
				System.out.println("quick main key :: ------------------ : " + hMap.getKey());
				System.out.println("quick key :: ------------------ : " + hMap.getKey());
				HashMap<Long, Long> map = hMap.getValue();
				for (Map.Entry<Long, Long> entry : map.entrySet()) {
					System.out.println(entry.getKey());
				}
				
				System.out.println("quick value-------------------- : ");
				
				for (Map.Entry<Long, Long> entry : map.entrySet()) {
					long value = entry.getValue();
					double milliseconds = value/1000000.0;
				 System.out.println(milliseconds);
				}
			}
			
			/*long time2 = System.nanoTime();
			long timeTaken = time2 - time1; 
			System.out.println("Total Time Taken :: " + timeTaken);
			*/
			
	}
	
	
	public void generateNumbers(int n, int option, File file){
		int[] ints = new int[n];
		BufferedWriter outputWriter = null;
		if(option == 1){
			ints = new Random().ints(0, n).limit(n).toArray();
		}else{
			ints = new Random().ints(0, n).limit(n).toArray();
			if(option == 2){
				Arrays.sort(ints);
			}else if(option == 3){
				ints = new int[n];
				int[] intRev = new int[n];
				intRev = new Random().ints(0, n).limit(n).toArray();
				Arrays.sort(intRev);
				int len = intRev.length;
				int q = 0;
				for(int p=len-1; p>=0;p--){

					ints[q] = intRev[p];
					q++;
				}
			}

		}  		//remove comment
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("IOException caught!! :: " + e);
			}
		}
		try {
			outputWriter = new BufferedWriter(new FileWriter(file));

		} catch (IOException e1) {
			System.out.println("IOException1 caught!! :: " + e1);
		}
		String arr = Arrays.toString(ints);
		arr=arr.replace("[", "");
		arr=arr.replace("]", "");
		arr=arr.replace(",", "");
		try {
			outputWriter.write(arr);
			outputWriter.flush();
			outputWriter.close();  
		} catch (IOException e1) {
			System.out.println("IOException2 caught!! :: " + e1);
		}
	}
	
	public static long getExecTime(int arr[], int option){
		long time1 = System.nanoTime();
		if(option == 1){
			insert(arr);
		}else if(option == 2){
			m_sort(arr, 1,arr.length);
		}else if(option == 3) {
			quick(arr, 0, arr.length-1);
		}
		long time2 = System.nanoTime();
		long timeTaken = time2 - time1;  
		return timeTaken;
	}
	public static long avgTime(long timeArray[]){
		long sum = 0;
		for(int j = 0; j< 10; j++){
			sum += timeArray[j];
		}
		long avg = sum/timeArray.length;
		return avg;
	}
	
	public void randomGen(int n1, int n2, int n){
		//System.out.println("n1 :: " + n1 + " n2 :: " + n2);
		int p1=0;
		//int p2;
		
		if(x < n){
			int[] ints = new Random().ints(n1, n2).limit(1).toArray();
			if(ints[0] != 0 /*&& ints[0] > ((n1+n2)/2)*/){
				//if(n1 > n2){
					//if(ints[0] > ((n1+n2)/2)){
					p1 = ints[0];
						randomArray[x] = p1;
						x++;
					//}
				//}
			}else{
				p1 = randomArray[x-1];
				randomArray[x] = p1;
				x++;
				//ints = new Random().ints(n1, n2).limit(1).toArray();
			}
			
			randomGen(n1,p1, n);
			randomGen(p1,n2,n);
		}else{
			//break;
		}
	}

}

