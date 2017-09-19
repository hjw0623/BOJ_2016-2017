//package basic.dp;
//
//public class test6_2 {
//
//	public static void main(String[] args) {
//		// 배열 길이 1<=n<=100_000
//		// 1<= arr[i]<=100
//		// arr[0]과 arr[length-1] 은 서로 연결
//
//		int arr[] = { 14, 6, 5, 11, 3, 9, 2, 10 };
//		System.out.println(solution(arr));
//
//		int arr2[] = { 1, 3, 2, 5, 100 };
//		System.out.println(solution(arr2));
//		
//	}
//
//	public static int solution(int sticker[]) {
//		if (sticker.length <= 3) {
//			int temp = 0;
//			for (int i = 0; i < sticker.length; i++) {
//				temp = Math.max(sticker[i], temp);
//			}
//			return temp;
//		}
//		// 첫 숫자를 무조건 고르는 경우와 고르지 않는 경우를 비교.
//
//		int dp1[] = new int[sticker.length - 1];
//		int dp2[] = new int[sticker.length];
//		
//		dp1[0] = sticker[0];
//		// 첫번째 스티커 무조건 선택한 경우.
//		for (int i = 2; i < dp1.length; i++) {
//			dp1[i] = Math.max(dp1[i - 2]+ sticker[i], dp1[i - 1]);
//		}
//
//		dp2[0] = 0;
//		dp2[1] = sticker[1];
//		// basis
//
//		// 첫번째 스티커 선택하지 않은 경우
//		for (int i = 2; i < sticker.length; i++) {
//			dp[i][1] = Math.max(dp[i - 2][1] + sticker[i], dp[i - 1][1]);
//		}
//
//		int answer = Math.max(dp1[sticker.length - 1], dp2[sticker.length - 1][1]);
//
//		for (int i = 0; i < dp.length; i++) {
//			// System.out.println(dp[i][0]+" || "+dp[i][1]);
//		}
//		return answer;
//	}
//}
