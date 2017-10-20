package heroes.samsung.test2;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution_3019 {

	static int board[][];
	// |
	static int type1_Y[] = { 0, 0, 0, 0 };
	static int type1_X[] = { -1, -2, -3, -4 };
	// | 90 회전
	static int type1_90X[] = { 0, 0, 0, 0 };
	static int type1_90Y[] = { 0, 1, 2, 3 };
	// type1
	static int type1X[][] = { type1_X, type1_90X };
	static int type1Y[][] = { type1_Y, type1_90Y };

	// ㅁ type2
	static int type2X[][] = { { -1, -1, -2, -2 } };
	static int type2Y[][] = { { 0, 1, 0, 1 } };

	// type3
	static int type3_X[] = { -1, -1, -2, -2 };
	static int type3_Y[] = { 0, 1, 1, 2 };
	// type 3의 90도 회전
	static int type3_90X[] = { -1, -2, -1, 0 };
	static int type3_90Y[] = { 0, 0, 1, 1 };
	// type3
	static int type3X[][] = { type3_X, type3_90X };
	static int type3Y[][] = { type3_Y, type3_90Y };

	// type 4
	static int type4_X[] = { -1, -1, 0, 0 };
	static int type4_Y[] = { 0, 1, 1, 2 };
	// type 4 90
	static int type4_90X[] = { -1, -2, -2, -3 };
	static int type4_90Y[] = { 0, 0, 1, 1 };
	// type 4
	static int type4X[][] = { type4_X, type4_90X };
	static int type4Y[][] = { type4_Y, type4_90Y };

	// type 5
	static int type5_X[] = { -1, -1, -2, -1 };
	static int type5_Y[] = { 0, 1, 1, 2 };
	// type 5 90
	static int type5_90X[] = { -1, -2, -3, -2 };
	static int type5_90Y[] = { 0, 0, 0, 1 };

	static int type5_180X[] = { -1, -1, 0, -1 };
	static int type5_180Y[] = { 0, 1, 1, 2 };

	static int type5_270X[] = { -1, -2, -3, -2 };
	static int type5_270Y[] = { 0, 0, 0, -1 };

	static int type5X[][] = { type5_X, type5_90X, type5_180X, type5_270X };
	static int type5Y[][] = { type5_Y, type5_90Y, type5_180Y, type5_270Y };

	// type 6
	static int type6_X[] = { -1, -1, -1, -2 };
	static int type6_Y[] = { 0, 1, 2, 3 };

	static int type6_90X[] = { -3, -2, -1, -1 };
	static int type6_90Y[] = { 0, 0, 0, 1 };

	static int type6_180X[] = { -1, -2, -2, -2 };
	static int type6_180Y[] = { 0, 0, 1, 2 };

	static int type6_270X[] = { -1, -1, 0, 1 };
	static int type6_270Y[] = { 0, 1, 1, 1 };

	static int type6X[][] = { type6_X, type6_90X, type6_180X, type6_270X };
	static int type6Y[][] = { type6_Y, type6_90Y, type6_180Y, type6_270Y };

	// type 7
	static int type7_X[] = { -1, -2, -1, -1 };
	static int type7_Y[] = { 0, 0, 1, 2 };

	static int type7_90X[] = { -1, -2, -3, -3 };
	static int type7_90Y[] = { 0, 0, 0, 1 };

	static int type7_180X[] = { -1, -1, -1, 0 };
	static int type7_180Y[] = { 0, 1, 2, 2 };

	static int type7_270X[] = { -1, -1, -2, -3 };
	static int type7_270Y[] = { 0, 1, 1, 1 };

	static int type7X[][] = { type7_X, type7_90X, type7_180X, type7_270X };
	static int type7Y[][] = { type7_Y, type7_90Y, type7_180Y, type7_270Y };
	static int C;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		int P = sc.nextInt();
		board = new int[106][C+1]; // 높이는 모르니까 일단 106
		String str = sc.nextLine();

		String arr[] = sc.nextLine().split(" ");
		int[] high = new int[C];
		for (int i = 0; i < arr.length; i++) {
			int height = Integer.parseInt(arr[i]);
			high[i] = height;
			for (int h = board.length - 1; h >= board.length - height - 1; h--) {
				board[h][i] = 1;
			}
		}

		if (P == 1) {
			check(type1X, type1Y, high);
		} else if (P == 2) {
			check(type2X, type2Y, high);
		} else if (P == 3) {
			check(type3X, type3Y, high);
		} else if (P == 4) {
			check(type4X, type4Y, high);
		} else if (P == 5) {
			check(type5X, type5Y, high);
		} else if (P == 6) {
			check(type6X, type6Y, high);
		} else if (P == 7) {
			check(type7X, type7Y, high);
		}
		System.out.println(cnt);
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

	static int cnt = 0;

	public static int check(int X[][], int Y[][], int height[]) {
		ArrayList<Integer> xList = new ArrayList<>();
		ArrayList<Integer> yList = new ArrayList<>();

		for (int c = 0; c < C; c++) {
			int cur = board.length - height[c] - 1;
			// System.out.println(" 현재 위치 c: "+c+ " 높이 : "+cur);
			for (int i = 0; i < X.length; i++) {// 현재의 패턴 (회전 포함) 조사
				boolean isValid = false;
//				xList = new ArrayList<>();
//				yList = new ArrayList<>();
				if(!xList.isEmpty()){
					xList.clear();
				}
				if(!yList.isEmpty()){
					yList.clear();
				}
				// 각각의 패턴의 nx, ny점을 체크한다.
				for (int k = 0; k < X[0].length; k++) { // 각 패턴의 경우에 nx, ny의 지점이
														// 0이 아니면 노카운트
					int nx = cur + X[i][k];
					int ny = c + Y[i][k];
					// System.out.println(nx+ " , "+ny);
					xList.add(nx);
					yList.add(ny);
					if (nx < 0 || ny < 0 || nx >= board.length || ny >= C) {
						isValid = true;
						break;
					}
					if (board[nx][ny] != 0) { // 다음 지점이 빈공간이 아니면 놓을 수 없다
						isValid = true;
						break;
					}
				}
				if (isValid) {
					for (int l = 0; l < xList.size(); l++) {
						xList.remove(0);
					}
					for (int l = 0; l < yList.size(); l++) {
						yList.remove(0);
					}
				}
				// 네 점 모두 유효하다면
				if (!isValid) {
					int nx = 0, ny = 0;
					boolean isValidCnt = false;
					for (int l = 0; l < xList.size(); l++) {
						nx = xList.get(l);
						ny = yList.get(l);
						board[nx][ny] = 2;
					}
//					 print();
					for (int l = 0; l < xList.size(); l++) {

						nx = xList.get(l);
						ny = yList.get(l);
						if (nx + 1 < board.length) {
							if (board[nx + 1][ny] == 0) {
								isValidCnt = true;
							}
						}
					}

					if (!isValidCnt) {
						cnt++;
//						print();
//						System.out.println();
					}
					for (int l = 0; l < xList.size(); l++) {
						nx = xList.get(l);
						ny = yList.get(l);
						board[nx][ny] = 0;
					}
					// System.out.println("=====원상복귀 ====");
					// print();

				}
			}
		}
		return cnt;
	}
}
