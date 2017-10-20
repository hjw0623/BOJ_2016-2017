package heroes.samsung.test2;

import java.util.Scanner;

public class Solution_14500 {

	static int M, N;
	static int map[][];
	static boolean visited[][];
	static int result = 0;
	// ㅗ
	static int type1X[] = { 0, 0, 0, -1 };
	static int type1Y[] = { 0, 1, 2, 1 };
	// ㅏ
	static int type2X[] = { 0, 1, 1, 2 };
	static int type2Y[] = { 0, 0, 1, 0 };
	// ㅜ
	static int type3X[] = { 0, 0, 0, 1 };
	static int type3Y[] = { 0, 1, 2, 1 };
	// ㅓ
	static int type4X[] = { 0, 1, 1, 2 };
	static int type4Y[] = { 0, 0, -1, 0 };

	static int typeX[][] = { type1X, type2X, type3X, type4X };
	static int typeY[][] = { type1Y, type2Y, type3Y, type4Y };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				int tmpMax = dfs(i, j, 0);
				if (tmpMax > result)
					result = tmpMax;
				visited[i][j] = false;
			}
		}
		// ㅗ ㅏ ㅓ ㅜ 검사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				for (int k = 0; k < 4; k++) {
					int tmp = 0;
					for (int l = 0; l < 4; l++) {
						int nx = i + typeX[k][l];
						int ny = j + typeY[k][l];
						if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
							tmp = -1;
							break;
						}
						tmp += map[nx][ny];
					}
					if (tmp > result)
						result = tmp;
				}
			}
		}
		System.out.println(result);
	}

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static int dfs(int i, int j, int depth) {
		int ret = 0;
		if (depth == 3) {
			return map[i][j];
		}

		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			int next = depth + 1;

			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				int tmp = dfs(nx, ny, next) + map[i][j];
				visited[nx][ny] = false;
				if (tmp > ret)
					ret = tmp;
			}

		}
		return ret;
	}
}
