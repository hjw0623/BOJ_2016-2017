package heroes.samsung.test1;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_4781 {
	static int item[];
	static int cost[];
	static int dp[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		dp = new int[10001];
		item= new int[5001];
		cost= new int [5001];
		while (true) {	
			int type = sc.nextInt();					 //사탕 종류 
			double budget = sc.nextDouble();			 // 소숫점 2자리
			int money = (int) ( budget * 100 +0.5);   	 // 정수예산.  *100해서 정수로 만든다.
			if (type == 0 && budget == 0.00)
				break;
		
			// 시작
			Arrays.fill(dp, -1);
			Arrays.fill(item, 0);
			Arrays.fill(cost, 0);
			for (int t = 1; t <= type; t++) {
				item[t] = sc.nextInt();
				cost[t] = (int) (sc.nextDouble() * 100 +0.5);
			}
			int minCost = 10000;
			for (int t = 1; t <= type; t++) {
				if (cost[t] < minCost) {
					minCost = cost[t];
				}
			}
			
			Math.max(0, napsack(type, minCost, money));
			System.out.println(dp[money]);

		}
	}

	public static int napsack(int type, int minCost, int budget) {
		// 최저예산값을 계산해둔다
		if (budget == 0|| budget < minCost)
			return 0;
		if (dp[budget] != -1)
			return dp[budget];
		// 현재 상황에서 item의 종류만큼 재귀가 가능하다.
		for (int i = 1; i <= type; i++) {
			if (budget - cost[i] >=0) {
				int tmp = item[i]+ napsack(type, minCost, budget-cost[i]);
				dp[budget] = Math.max(dp[budget] , tmp);
			}
		}
		return dp[budget] ;
	}
}
