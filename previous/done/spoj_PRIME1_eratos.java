package previous.done;

import java.util.ArrayList;
import java.util.Scanner;

//http://www.spoj.com/problems/PRIME1/
public class spoj_PRIME1_eratos {
	static int testcase;
	static int from;
	static int to;
	static int isPrime[];
	static int time = 100_000_000; // 10억

	static ArrayList<Integer> primeMemo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		testcase = sc.nextInt();
		setPrime(time);

		for (int i = 0; i < testcase; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			

			getPrime(from, to);
		}
	}

	// ex. 1~num까지의 소수 찾아 기입.
	public static void setPrime(int num) {
		primeMemo = new ArrayList<Integer>();
		primeMemo.add(2);

		for (int i = 2; i <= num; i++) {
			for (int j = 0; j<primeMemo.size(); j++) {
				if (i % primeMemo.get(j) == 0) {
					break;
				}
				if(j+1==primeMemo.size()){
					primeMemo.add(i);
				}
			}
		}
	}

	public static void getPrime(int from, int to) {
		for (Integer result : primeMemo) {
			if(result>=from && result<=to){
				System.out.println(result);
			}
		}


	}
}
