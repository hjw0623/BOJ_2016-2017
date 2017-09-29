package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//다음 순열 

/*
4
1 2 3 4
1 2 4 3 
1 3 2 4
1 3 4 2
1 4 2 3 
1 4 3 2

https://soooprmx.com/archives/5523

 */
public class BOJ_10972 {
	static int arr[];
	static int number;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		number = Integer.parseInt(br.readLine());
		arr = new int[number];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < number; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int count = 1;
		for (int i = 0; i < number - 1; i++) {
			if (arr[i] > arr[i + 1])
				count++;
		}
		if (count == number) {
			System.out.println(-1);
		} else {
			solveNextPermutation(arr);
			print();
		}
		
	}

	public static void print() {
		for (int i = 0; i < number; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
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

	public static void solvePrevPermutation(int arr[]) {
		int k = 0;
		// a[k] > a[k+1]인 가장 큰 k를 찾는다.
		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i - 1] > arr[i]) {
				k = i - 1;
				break;
			}
		}
		int l = 0; // k이후 a[k]보다 큰 값을 가진 가장 먼 인덱스
		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i] < arr[k]) {
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
