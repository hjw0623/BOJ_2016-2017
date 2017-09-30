package heroes.basic.dfs;

import java.util.Scanner;

//1,2,3 더하기 
//https://www.acmicpc.net/problem/9095
public class BOJ_9095 {
	static int dp[];
	public static int solve(int n){
		//basis
		if(dp[n]!=0)
			return dp[n];
		if(n<=3)
			return dp[n];
		//recursive
		else{
			dp[n] = solve(n-1)+solve(n-2)+solve(n-3);
		}
		return dp[n];	
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new int[12];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for(int i=1; i<=N; i++){
			int tmp = sc.nextInt();
			System.out.println(solve(tmp));
		}		
	}
}
