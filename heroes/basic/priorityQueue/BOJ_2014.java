package heroes.basic.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
http://mygumi.tistory.com/183
4 19
2 3 5 7
 */
public class BOJ_2014 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		long initPrime[] = new long[K];
		st = new StringTokenizer(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<>();
		HashMap<Long, Integer> map = new HashMap<>();
		for (int i = 0; i < K; i++) {
			initPrime[i] = Integer.parseInt(st.nextToken());
			pq.add(initPrime[i]);
			map.put(initPrime[i], 1);
		}
		long head = 0;
		for (int i = 0; i < N; i++) {
			head = pq.poll();
			for (int j = 0; j < K; j++) {
				long newVal = head * initPrime[j];
				System.out.println(newVal);
				pq.add(newVal);
				map.put(newVal, 1);
				
				// 중복된 수 피하기
				if(head%initPrime[j]==0){
					break;
				}
			}
		}
		System.out.println(head);
	}
}
