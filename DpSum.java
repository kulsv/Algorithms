import java.util.*;

public class DpSum {

	public static void main(String args[])
	{
		System.out.println("Enter the value:: ");
		Scanner s=new Scanner(System.in);
		long n=s.nextLong();
		long count = calcSum(n);
		System.out.println("Number of ways :: "+ count);
		s.close();
	}

	public static long calcSum(long n) {
		HashMap<Long, Long> entry = new HashMap<Long, Long>();       // key as an input number from 0 to n and value is number of ways in which it can be added using 1, 3, 4
		entry.put(0l, 1l);
		entry.put(1l, 1l);
		entry.put(2l, 1l);
		entry.put(3l, 2l);

		if(n > 4) {
			for(long i = 4; i <= n; i++) {
				long sum1 = 0; 
				if( null != entry.get(i-1))
					sum1 = entry.get(i-1)%100000;
				long sum3 = 0; 
				if( null != entry.get(i-3))
					sum3 = entry.get(i-3)%100000;
				long sum4 = 0;
				if( null != entry.get(i-4))
					sum4 = entry.get(i-4)%100000;

				long sum = sum1 + sum3 + sum4;
				sum = sum %100000;
				entry.put(i, sum);

				
			}
		}
		return entry.get(n);
	}
	
}
