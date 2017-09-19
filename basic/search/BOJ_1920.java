package basic.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1920 {
	static int[] arr;
	static int N, M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<M; i++){
			int target = Integer.parseInt(st.nextToken());
			System.out.println(BinarySearch(arr, target));
		}

	}
	public static int BinarySearch(int []arr, int target){
		int mid=0;
		int left =0; 
		int right = arr.length-1;
		
		while(right>=left){
			mid = (left+right)/2;
			
			if(target==arr[mid]){
				return 1;
			}
			if(target>arr[mid]){
				left = mid+1;
			}else{
				right = mid-1;
			}
		}
		
		return 0;
	}
}
