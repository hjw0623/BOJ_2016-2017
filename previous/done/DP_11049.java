package previous.done;

import java.util.Scanner;

public class DP_11049 {
	static int mat[][];
	static int dp[][];
	static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		n = sc.nextInt();
		mat = new int [n][2];
		dp = new int [n][n];
		for(int i=0; i<n; i++){
			int head = sc.nextInt();
			int tail = sc.nextInt();
			mat[i][0] = head;
			mat[i][1] = tail;
		}	
		System.out.println(solve(0, n-1));
	}
	public static int solve(int i, int j){
		if(i==j) return 0;
		if(dp[i][j]>0 )return dp[i][j];
		if(i+1 ==j){
			return mat[i][0]*mat[i][1]*mat[j][1];
		}
		for(int k=i; k<=j-1; k++){
			int t1 = solve(i,k);
			int t2 = solve(k+1, j);
			int temp = t1+t2+mat[i][0]*mat[k][1]*mat[j][1];
			if(dp[i][j]>temp ||dp[i][j]==0){
				dp[i][j] =temp;
			}
		}
		return dp[i][j];
	}
}
