package heroes.samsung.test2;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution_3019_re {

	static int board[][] = new int[106][101]; // 106, 101
	static int height[] = new int[101]; // P개 열에 대한 높이
	static int C, P;
	static int cnt = 0;
	// 현재 0인 점을 기준으로 패턴을 그린다
	static int[][] row1 = { { 1, 1, 1 }, { 0, 0, 0 } };
	static int[][] col1 = { { 0, 0, 0 }, { 1, 1, 1 } };
	// 네모
	static int[][] row2 = { { 1, 0, -1 } };
	static int[][] col2 = { { 0, 1, 0 } };
	// 3 __|-
	static int[][] row3 = { { 0, 1, 0 }, { 1, 0, 1 } };
	static int[][] col3 = { { 1, 0, 1 }, { 0, -1, 0 } };
	// 4
	static int[][] row4 = { { 0, -1, 0 }, { 1, 0, 1 } };
	static int[][] col4 = { { 1, 0, 1 }, { 0, 1, 0 } };

	// 5 ㅗㅏ ㅓ ㅜ
	static int[][] row5 = { { 0, 1, -1 }, { 1, 0, 1 }, { 1, 0, 1 }, { 1, 0, 0 } };
	static int[][] col5 = { { 1, 0, 1 }, { 0, 1, -1 }, { 0, -1, 1 }, { 0, -1, 2 } };

	static int[][] row6 = { { 0, 0, 1 }, { 1, 1, -2 }, { 1, 0, 0 }, { 1, 1, 0 } };
	static int[][] col6 = { { 1, 1, 0 }, { 0, 0, 1 }, { 0, 1, 1 }, { 0, 0, -1 } };

	static int[][] row7 = { { 1, -1, 0 }, { 1, 1, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
	static int[][] col7 = { { 0, 1, 1 }, { 0, 0, 1 }, { 0, -1, -1 }, { 1, 0, 0 } };

	static int[][][] rowtype = { row1, row2, row3, row4, row5, row6, row7 };
	static int[][][] coltype = { col1, col2, col3, col4, col5, col6, col7 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		P = sc.nextInt() - 1; // 패턴이 0~6까지

		String str = sc.nextLine();

		String arr[] = sc.nextLine().split(" ");
		int[] height = new int[C];
		for (int col = 0; col < C; col++) {
			int high = Integer.parseInt(arr[col]);
			height[col] = high;
			for (int row = 0; row < height[col]; row++) {
				board[row][col] = 1;
			}
		}
		for (int col = 0; col < C; col++) {
			for (int i = 0; i < rowtype[P].length; i++) {
				board[height[col]][col] = 2; // 현재 위치를 블록칠한다.
				solve(height[col], col, 0, rowtype[P][i], coltype[P][i]);
				board[height[col]][col] = 0;
			}

		}
		// print();
		System.out.println(cnt);
	}

	public static void solve(int row, int col, int depth, int[] dRow, int[] dCol) {
		if (depth == 3) {
			if (isPossible())
				cnt++;
			return;
		}
		int nextRow = row + dRow[depth];
		int nextCol = col + dCol[depth];
		// 범위밖이면 노카운트, 0이 아니면 노카운트
		if (nextRow < 0 || nextCol < 0 || nextRow >= 105 || nextCol >= C)
			return;
		if (board[nextRow][nextCol] != 0)
			return;

		board[nextRow][nextCol] = 2;
		solve(nextRow, nextCol, depth + 1, dRow, dCol);
		board[nextRow][nextCol] = 0;
	}

	public static boolean isPossible() {
		for (int col = 0; col < C; col++) {
			boolean rowCheck = true;
			for (int row = height[col]; row < 105; row++) {
				if (board[row][col] == 0 && rowCheck) {
					rowCheck = !rowCheck;
					continue;
				}
				if (board[row][col] > 0 && !rowCheck) {
					return rowCheck;
				}
			}
		}
		return true;
	}

	public static void print() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------");
	}

}
