package heroes.samsung.test2;

import java.util.Arrays;
import java.util.Scanner;

//10
//1 1
//1 2
//1 3
//1 4
//1 5
//1 6
//1 7
//1 8
//2 9
//1 10
public class Solution_14501 {
	static int cost[];
	static int pay[];
	static int dp[]; // 날짜가 idx일때 이후 얻을 금액의 최대값
	static int dp2[];
	// dp[idx] = max(dp[idx+1], dp[idx+cost[i]]+pay[i]);
	static final int INF = 1000000;
	static int N;
	static int result = 0;

	public static void main(String[] args) throws InterruptedException {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		cost = new int[N + 1];
		pay = new int[N + 1];
		dp = new int[N + 2];
		dp2 = new int[N+1];
		Arrays.fill(dp2, -1);
		for (int i = 1; i <= N; i++) {
			cost[i] = sc.nextInt();
			pay[i] = sc.nextInt();
		}
		// 맨 끝에서부터 거꾸로 탐색해주어야 한다.
		for (int i = N; i >= 1; i--) {
			// 현재 위치에 대해서
			// 기한내 못 끝낼 일이라면 +1일의 값과 0을 비교하여 넣어준다.
			if (cost[i] + i > N + 1) {
				dp[i] = Math.max(dp[i + 1], 0);
			} else if (cost[i] + i <= N + 1) { // 기한내 끝맞출 수 있는 경우
				int accept = pay[i]; // 수락할 때 추가금
				dp[i] = Math.max(dp[i + cost[i]] + accept, dp[i + 1]);
				// 수락하는 경우 dp[i+cost[i]]+pay[i]
				// 수락하지 않는 경우 dp[i+1]의 값을 비교하여 dp[i]에 저장.
				// System.out.println(i+ " "+dp[i]);
			}
		}
		System.out.println(dp[1]);
		
		System.out.println(dfs(1));
	}

	public static int dfs(int day) {
		if (day == N + 1)
			return 0;
		if (day > N + 1)
			return -INF;
		if(dp2[day]!=-1)
			return dp2[day];
		
		return dp2[day] = Math.max(dfs(day+1), dfs(day+cost[day])+pay[day]);
	}
}