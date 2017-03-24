package algorithm_intermid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1149 {
	static int n;
	static int house[][];
	static int d[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		house = new int[n][n];
		d = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(house[i][j] + " ");
			}
//			System.out.println();
		}
		d[0][1] = house[0][1];
		d[0][2] = house[0][2];
		d[0][0] = house[0][0];
		// bottom up
		for (int i = 1; i < n; i++) {
			d[i][0] = min(d[i - 1][1], d[i - 1][2]) + house[i][0];
			d[i][1] = min(d[i - 1][0], d[i - 1][2]) + house[i][1];
			d[i][2] = min(d[i - 1][0], d[i - 1][1]) + house[i][2];
		}
		int result = Integer.MAX_VALUE;
		for(int i=0;i<3;i++){
			if (result > d[n-1][i])
				result = d[n-1][i];
		}
		System.out.println(result);
	}
	public static int min(int a, int b) {
		return (a > b) ? b : a;
	}
}
