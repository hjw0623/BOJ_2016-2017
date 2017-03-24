package algorithm_intermid;

import java.util.Scanner;

public class DP_11066 {
	static int test;
	static int k;
	static int input[];
	static int dp[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		test = sc.nextInt();
		for(int i=0; i<test; i++){
			k = sc.nextInt();
			input = new int[k];
			dp = new int [k][k];
			for(int j=0; j<input.length; j++){
				input[j] = sc.nextInt();
			}	
			System.out.println(solve(0,k-1));
		}
	}
	public static int solve(int i, int j){
		if(i==j)return 0;
		if(dp[i][j]!=0) return dp[i][j]; //memo
		int subsum=0;
		for(int l=i; l<=j; l++){
			subsum+=input[l];
		}
		for(int k=i; k<=j-1; k++){
			int tmp =solve(i,k)+solve(k+1,j)+subsum;
			if(dp[i][j]>tmp|| dp[i][j]==0){
				dp[i][j]=tmp;
			}
		}
		return dp[i][j];
	}
}
