package previous.done;

import java.util.Scanner;

public class DP_2293 {
	static int n, k;
	static int coin[];
	static int dp[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		k = sc.nextInt();
		dp = new int[k + 1];
		coin = new int[n];
		for (int j = 0; j < n; j++) {
			coin[j] = sc.nextInt();
		}
		dp[0] = 1;//basis
		//dp
		for(int i=0; i<n; i++){
			for(int j=coin[i]; j<=k;j++){ //coin[i]부터 시작해서,k원까지 조합을 구하고자 한다.
										  //i번째 동전을 사용하고, k원을 만들고자.
				dp[j]+= dp[j-coin[i]];
			}
		}
		System.out.println(dp[k]);
	}
}
