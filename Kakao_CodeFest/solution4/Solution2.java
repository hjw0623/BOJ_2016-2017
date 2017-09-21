package Kakao_CodeFest.solution4;

import Kakao_CodeFest.solution4.Solution.Node;
import Kakao_CodeFest.solution4.Solution.NodePair;

public class Solution2 {
	static int MOD = 20170805;
	static final int LEFT = 0;
	static final int UP = 1;
	static int dp[][][] = new int[501][501][2];// from up, from left;

	public static int solution(int m, int n, int[][] cityMap) {
		int answer = 0;

		dp[0][0][LEFT] = dp[0][0][UP] = 1;
		// 첫번째 열 채운다
		for (int i = 1; i < m; i++) {
			// 0이면 LEFT
			if (cityMap[i][0] == 0) {
				dp[i][0][UP] = 1;
			} else if (cityMap[i][0] == 2) {
				dp[i][0][UP] = 1;
			}
			// 1이면 초기화 값 넣기 종료
			else
				break;
		}

		// 첫번째 행을 채운다
		for (int j = 1; j < n; j++) {
			// 0이면 UP
			if (cityMap[0][j] == 0) {
				dp[0][j][LEFT] = 1;
			} else if (cityMap[0][j] == 2) {
				dp[0][j][LEFT] = 1;
			} else {
				break;
			}
		}

		

		// 미로 진행

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (cityMap[i][j] == 0) {
					dp[i][j][LEFT] += (dp[i][j - 1][UP] % MOD + dp[i - 1][j][LEFT] % MOD) % MOD;
					dp[i][j][UP] += (dp[i - 1][j][LEFT] % MOD + dp[i][j - 1][UP] % MOD) % MOD;
				} else if (cityMap[i][j] == 2) {
					dp[i][j][LEFT] += dp[i][j - 1][UP] % MOD;
					dp[i][j][UP] += dp[i - 1][j][LEFT] % MOD;
				} else if (cityMap[i][j] == 1) {
					dp[i][j][LEFT] = 0;
					dp[i][j][UP] = 0;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(dp[i][j][0] + " ");
			}System.out.println();
		}
		System.out.println("----");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(dp[i][j][1] + " ");
			}System.out.println();
		}
		
		answer = (dp[m - 1][n - 1][LEFT] + dp[m - 1][n - 1][UP]) % MOD;
		return answer;
	}

	public static void main(String[] args) {
		int arr[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }; // 6
		int arr2[][] = { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } }; // 2
		// System.out.println(Integer.MAX_VALUE);
		System.out.println(solution(3, 3, arr));
		System.out.println(solution(3, 6, arr2));
	}

}
