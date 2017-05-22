package boj;
//https://www.acmicpc.net/problem/14500
import java.util.Arrays;
//LEFT 0, UP 1, RIGHT 2, DOWN 3
import java.util.Scanner;

/*
7
3 10
5 20
1 10
1 20
2 15
4 40
2 200

10
1 1
1 2
1 3
1 4
1 5
1 6
1 7
1 8
1 9
1 10

 */

public class BOJ_14501 {
	static int N;
	static int days[];
	static int income[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		days = new int[N + 1];
		income = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			days[i] = sc.nextInt();
			income[i] = sc.nextInt();
		}

		solve(0,1);
		System.out.println(result);
	}

	static int result = 0;
	static void solve(int sum, int i) {
		// i가 N보다 크면 sum을 반환.
		if (i > N+1 )
			return ;		
		if (i == N+1 ){
			result = result > sum ? result : sum;
			return;
		}
		solve(sum, i + 1);
		solve(sum + income[i], i + days[i]);
		

	}
}
