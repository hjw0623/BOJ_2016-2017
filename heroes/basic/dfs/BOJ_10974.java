package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//모든 순열 
public class BOJ_10974 {
	static int arr[] ;
	static int number;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		number = Integer.parseInt(br.readLine());
		arr = new int[number];
		for(int i=1; i<=number;i++){
			arr[-1+i] = i;
		}
		print();
		int totalNum = factorial (number);
//		System.out.println(totalNum);
		for(int i=1; i<totalNum; i++){
			solveNextPermutation(arr);
			print();
		}
	}
	public static void print(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.length-1; i++){
			sb.append(arr[i]+" ");
		}sb.append(arr[arr.length-1]);
		System.out.println(sb.toString());
	}
	public static int factorial(int n){
		if(n==1 )return 1;
		else{
			return n*factorial(n-1);
		}
	}
	public static void solveNextPermutation(int arr[]) {
		int k = 0;
		// a[k] < a[k+1]인 가장 큰 k를 찾는다.
		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i - 1] < arr[i]) {
				k = i - 1;
				break;
			}
		}
		int l = 0; // k이후 a[k]보다 큰 값을 가진 가장 먼 인덱스
		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i] > arr[k]) {
				l = i;
				break;
			}
		}
		swap(k, l);
		int auxIdx = 0;
		for (int j = k + 1; j < (k + 1 + arr.length) / 2; j++) {
			// System.out.println(j);
			swap(j, arr.length - 1 - auxIdx);
			auxIdx++;
		}
	}
	public static void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
