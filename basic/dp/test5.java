package basic.dp;

public class test5 {

	public static void main(String[] args) {

		int arr[][] = { { 1, 2, 3, 5 }, { 5, 6, 7, 8 }, { 4, 3, 2, 1 } };
		System.out.println(solution(arr));
	}

	public static int solution(int[][] land) {
		int size = land.length;
		int dp[][] = new int[size][4];
		for (int j = 0; j < 4; j++) {
			dp[0][j] = land[0][j];
		}
		// dp bottom up
		for (int i = 1; i < land.length; i++) {
			for (int j = 0; j < 4; j++) {
				int temp = 0;
				for (int k = 0; k < 4; k++) {
					if (k == j)
						continue;
					temp = Math.max(dp[i - 1][k], temp);
				}
				dp[i][j] = land[i][j] + temp;
			}
		}
		int answer = 0;

		for (int j = 0; j < 4; j++) {
			if (answer < dp[size - 1][j])
				answer = dp[size - 1][j];
		}

		return answer;
	}
}
