package heroes.basic.dfs;

import java.util.Scanner;

//N-Queen

public class BOJ_9663 {
	static int board[][];
	static int N = 0;
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N][N];
		int ret = 0;
		if (N == 1) {
			System.out.println(1);
		} else if (N == 2 || N == 3) {
			System.out.println(0);
		} else {
//			for (int j = 0; j < N; j++) {
				NQueen(0);
//			}
			System.out.println(result);
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// i,j에 놓는게 유효한지 검사
	public static boolean check(int i, int j) {
		// 놓기전에 검사부터 한다 .
		// 1. 세로에 다른 퀸이 있는가?
		for (int k = 0; k < N; k++) {
			if (k == i)
				continue;
			if (board[k][j] == 1) {
//				System.out.println("가로상에 퀸 존재 ");
				return false;
			}
		}
		// 2. 대각선에 다른 퀸이 있는가?
		// 2-1 대각선 \ 위
		int k = 1;
		while ((i - k) >= 0 && (j - k) >= 0) {
			if (board[i - k][j - k] == 1) {
				return false;
			}
			k++;
		}

		// 2-2 대각선 \ 아래
		k = 1;
		while ((i + k) < N && (j + k) < N) {
			if (board[i + k][j + k] == 1) {
				return false;
			}
			k++;
		}
		k = 1;
		// 2-3 대각선 / 위
		while ((i - k) >= 0 && (j + k) < N) {
			if (board[i - k][j + k] == 1) {
//				System.out.println("대각선에 퀸 존재 ");
				return false;
			}
			k++;
		}
		k = 1;
		// 2-4 대각선 / 아래
		while ((i + k) < N && (j - k) >= 0) {
			if (board[i + k][j - k] == 1) {
//				System.out.println("대각선에 퀸 존재 ");
				return false;
			}
			k++;
		}
		return true;
	}

	// i행의 j를 채우고자 한다.
	public static void NQueen(int i) {

		if (i == N) {
//			 System.out.println("===N-Queen 경우의 수 추가===");
//			 print();
			// System.out.println();
			result++;
			return;
		}
		// 재귀 탐색한다.
		for (int k = 0; k < N; k++) {
			board[i][k] = 1; // i,j를 방문했다고 하자.
			if (check(i, k)) {
				NQueen(i + 1);
			}
			board[i][k] = 0; // 보드의 퀸을 해제한다.
		}

	}
}
