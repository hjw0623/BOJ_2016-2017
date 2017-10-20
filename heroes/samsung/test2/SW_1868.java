package heroes.samsung.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//2
//3
//..*
//..*
//**.
//5
//..*..
//..*..
//.*..*
//.*...
//.*...
//파핑파핑 지뢰찾기 
public class SW_1868 {
	static int T;
	static int N;
	static int map[][];
	static char board[][];
	static boolean visited[][];

	static class Point {
		int x;
		int y;

		public Point() {
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int mx[] = { -1, 1, 0, 0 };
	static int my[] = { 0, 0, -1, 1 };
	// 8방향
	static int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int check[][];

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			board = new char[N][N];
			visited = new boolean[N][N];
			check = new int[N][N];
			for (int n = 0; n < N; n++) {
				String tmp = br.readLine();
				board[n] = tmp.toCharArray();
			}
			// bfs queue
			Queue<Point> qq = new LinkedList<Point>();
			// 인접 지뢰 카운트
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '*') {
						map[i][j] = 9;
						continue;
					}
					int nierMine = 0;
					// 별표가 아닌 지점에서 인접 지뢰 개수 구한다.
					for (int k = 0; k < 8; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx < 0 || ny < 0 || nx >= N || ny >= N)
							continue;
						if (board[nx][ny] == '*') {	//
							nierMine++;
						}
					}
					map[i][j] = nierMine;
				}
			}
//			print2();
//			System.out.println("====");
			int click = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0 && !visited[i][j]) { // 첫 시작점을 넣는다.
						qq.add(new Point(i, j));
						click++;
						// bfs 시작
						while (!qq.isEmpty()) {
							Point cur = qq.poll();
							int cx = cur.x;
							int cy = cur.y;
							check[cx][cy] = 1;
							visited[cx][cy] = true; // 방문 체크

							// 인접한 8방향에 대해서 지뢰와 인접한 영역은 방문 체크해준다.
							for (int k = 0; k < 8; k++) {
								int nx = cx + dx[k];
								int ny = cy + dy[k];
								if (nx < 0 || ny < 0 || nx >= N || ny >= N)
									continue;
								if (map[nx][ny] > 0 && map[nx][ny] != 9) {
									visited[nx][ny] = true;
									check[nx][ny] = 1;
								}else  if(map[nx][ny]==0 && !visited[nx][ny]){
									qq.add(new Point(nx, ny));
									visited[nx][ny] = true;
								}
							}
						}
					}
				}
			}
			// 인접한 0이 없는 나머지 빈칸개수를 더한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0 && !visited[i][j] && map[i][j] != 9) {
						// System.out.println("여기 안오냐 ");
						click++;
						visited[i][j] = true;
					}
				}
			}
			System.out.println("#" + t + " " + click);
			click = 0;
		}
	}

	public static void print4() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					System.out.print(1 + " ");
				else
					System.out.print(0 + " ");
			}
			System.out.println();
		}
	}

	public static void print3() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(check[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void print2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + "");
			}
			System.out.println();
		}
	}
}
