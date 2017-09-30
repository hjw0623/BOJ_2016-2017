package heroes.basic.dfs;

import java.util.Scanner;

/*
 * 
숫자 1, 2, 3으로만 이루어지는 수열이 있다. 임의의 길이의 인접한 두 개의 부분 수열이 동일한 것이 있으면, 그 수열을 나쁜 수열이라고 부른다.
그렇지 않은 수열은 좋은 수열이다.

다음은 나쁜 수열의 예이다.

33
32121323
123123213
다음은 좋은 수열의 예이다.

2
32
32123
1232123
길이가 N인 좋은 수열들을 N자리의 정수로 보아 그중 가장 작은 수를 나타내는 수열을 구하는 프로그램을 작성하라.
예를 들면, 1213121과 2123212는 모두 좋은 수열이지만 그 중에서 작은 수를 나타내는 수열 1213121이다.

*/

/*

풀이 
http://stack07142.tistory.com/114
- 수열 시작은 무조건 1부터
- 나쁜 수열인지 체크*
- Bactracking 구성


*나쁜 수열인지 체크*
1) 나쁜수열 체크
2-1) 나쁜수열이면 돌아가기
2-2) 좋은 수열이면 
  3-1) 길이 다 채운 경우 -> return 후 모든 재귀 종료
  3-2) 길이 못 채운 경우 
  	4) 숫자 추가 
 */
public class BOJ_2661 {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int n = sc.nextInt();
		String str = "";
		dfs (0, n, str);
	}
	public static boolean isBadSeries(String str){
		int len = 1;
		while(len <= str.length()/2){
			int dec = len*2; //비교 대상의 길이를 고려 
			for(int i=0;i<=str.length()-dec ;i++){
				if(str.substring(i, i+len).equals(str.substring(i+len, i+dec))){
					return true;
				}
			}
			len++;
		}
		return false;
	}
	//현재 길이가 len이고, 다음에 붙일게 next이고, 총 n 길이까지 수열을 만들 때.
	public static int dfs(int len,  int n, String str){
		if(isBadSeries(str)){
			return -1;
		}
		if(len ==n){
			System.out.println(str);
			return 0;
		}
		//무조건 1을 붙이는 것부터 재귀탐색 시작하므로 최솟값이 출력 
		for(int next = 1; next<=3; next++){
			str = str+next;
			if(dfs(len+1, n, str)==0){
				return 0;
			}
			str = str.substring(0, str.length()-1); //원상복귀 
		}
		return -1;
	}
}
