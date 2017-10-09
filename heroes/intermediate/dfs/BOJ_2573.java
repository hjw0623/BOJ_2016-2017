package heroes.intermediate.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
	static int iceBerg[][];
	static int N, M;

	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		iceBerg = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				iceBerg[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int numOfIsland = 0;
		int time = 0;
		while (numOfIsland < 2) {
			int check = 0;
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					check += iceBerg[i][j];
				}
			}
			if(check==0){
				time =0;
				break;
			}
			time++;
			meltDown();
			numOfIsland=0;
			boolean visited[][] = new boolean[N][M];
			for (int i = 0; i < N ; i++) {
				for (int j = 0; j < M ; j++) {
					boolean isLand = false;
					if (iceBerg[i][j] == 0)
						continue;
					// else if (visited[i][j]) //방문한 대륙
					// continue;
					if(iceBerg[i][j]!=0 && visited[i][j]!=true){
						isLand = true;
						Queue<Pair> queue = new LinkedList<Pair>();
						queue.add(new Pair(i, j));
						visited[i][j] = true;
						while (!queue.isEmpty()) {
							Pair cur = queue.poll();
							int cx = cur.x;
							int cy = cur.y;
							for (int k = 0; k < 4; k++) {
								int nx = cx + dx[k];
								int ny = cy + dy[k];

								if (nx < 0 || ny < 0 || nx >= N || ny >= M)
									continue;
								if (iceBerg[nx][ny] != 0) {
									if (!visited[nx][ny]) {
										queue.add(new Pair(nx, ny));
										visited[nx][ny] = true;
									}
								}
							}
						}
						numOfIsland++;
					}
				}
			}
		}
		System.out.println(time);
	}

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static void meltDown() {
		int melting[][] = new int[N][M];
		int neighbor = 0; // 인접한 바닷물
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < M ; j++) {
				neighbor = 0;
				if (iceBerg[i][j] == 0)
					continue;
				else {
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx < 0 || ny < 0 || nx >= N || ny >= M)
							continue;
						if (iceBerg[nx][ny] == 0){
//							System.out.println(i+","+j+"옆의 "+nx+","+ny+"는 바닷물 ");
							melting[i][j]--;
						}
					}
				}
			}
		}
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < M ; j++) {
				if (iceBerg[i][j] == 0)
					continue;
				else {
					iceBerg[i][j] += melting[i][j];
					if (iceBerg[i][j] < 0)
						iceBerg[i][j] = 0;
				}
			}
		}
//		System.out.println("after melting");
//		print();
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(iceBerg[i][j] + " ");
			}
			System.out.println();
		}
	}
}
