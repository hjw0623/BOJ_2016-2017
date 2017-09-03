package previous.done;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DP_11399_ATM {
	static int N;
	static int []Cumulative;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		N = sc.nextInt();
		ArrayList<Integer> P = new ArrayList<Integer>();
		
		Cumulative = new int [N];
		for(int i=0; i<N; i++){
			int k = sc.nextInt();
			P.add(k); 
		}
		Collections.sort(P);
		Cumulative[0] = P.get(0);
		for(int i=1; i<N; i++){
			Cumulative[i] = Cumulative[i-1]+P.get(i);
		}
		int sum =0;
		for(int i=0; i<N; i++){
			sum+= Cumulative[i];
		}
		System.out.println(sum);
	}

}