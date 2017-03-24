package algorithm_intermid;

import java.util.Scanner;

public class DP_2228_un {
	static int n, m;
	static int number[];
	static int dp[][]; 		// dp[i][j]는 number[1~i]까지 구간을 j개의 구간으로 나누고
							// 구간에 속한 수들의 총합의 최대값을 구하는 문제.
	static boolean c[][];
	static int subSum[]; // 0~i까지의 부분 합
    public static int min = -32786*101;

	static int solve(int n, int m){
		if(m==0) return 0;					//M개의 구간이 모두 존재.
		if(n<=0) return min;	//n<=0인 상황에서 m>0인 경우, 조건 충족x
		if(c[n][m]) return dp[n][m]; //memoization
		c[n][m] =true; //메모이제이션을 위한 체크 
		
		//case 1 : n번째 수를 구간에 포함하지 않는 경우.
		dp[n][m] = solve(n-1,m); 
		//case 2 : n번째 수를 구간에 포함하는 경우.
		//dp[i][j] = dp[k-2][j-1] + subSum[i][k];
		for(int k=1; k<=n; k++){
			int temp = solve(k-2, m-1)+subSum[n]- subSum[k-1];
				dp[n][m] = Math.max(dp[n][m],temp);
		}
		return dp[n][m];
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		number = new int[n+1];
		subSum = new int[n+1];
		for (int i = 1; i <= n; i++) {
			number[i] = sc.nextInt();
			subSum[i] = subSum[i-1]+number[i];
		}
	
		dp = new int [n+1][m+1];
		c = new boolean[n+1][m+1];
		System.out.println(solve(n,m));
	}

}
