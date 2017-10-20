package heroes.samsung.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//로봇 청소기 
public class Solution_14503 {
	static int N, M;
	static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	static int map[][];
	static boolean visited[][];
	static final int WALL = 1;
	static final int BLANK = 0;
	static final int CLEAN = 2;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		st = new StringTokenizer(br.readLine());
		// 초기 위치와 방향
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int ret = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					ret++;
				}
			}
		}
		// cnt++;
		visited[x][y] = true;
		map[x][y] = CLEAN;
		if (d==NORTH){
			d = 0;
		}else if(d==EAST){
			d = 3;
		}else if(d ==SOUTH){
			d = 2;
		}else if(d==WEST){
			d = 1;
		}
		go(x, y, d, 0);
		// print();
		cnt = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (map[i][j] == 2) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);

	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// 0, 1, 2 , 3    	
	// 북, 서, 남, 동 		
	static int dx[] = { -1, 0, 1, 0 }; // { 0, 1, 0, -1 };
	static int dy[] = { 0, -1, 0, 1 };// { -1, 0, 1, 0 };

	static void go(int i, int j, int d, int count) {
		// System.out.println("시작 : " + i + " ," + j + " 방향 : " + d);
		// step 2
		// 북의 왼쪽은 서쪽, 동의 왼쪽은 북, 남의 왼쪽은 동, 서의 왼쪽은 남
		int nx;
		int ny;

		// 탐색 방향
		if (d + 1 != 4) {
			nx = i + dx[d + 1];
			ny = j + dy[d + 1];
		} else {
			nx = i + dx[0];
			ny = j + dy[0];
		}
		if (nx < 0 || ny < 0 || nx >= N || ny >= M)
			return;
		// case 2 -1
		// 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
		if ((!visited[nx][ny]) && map[nx][ny] == 0) {
			// System.out.println("case 2-1");
			d = d + 1;
			if (d == 4)
				d = 0;
			// step 1
			visited[nx][ny] = true;
			map[nx][ny] = CLEAN;

			// System.out.println(nx + ", " + ny + " 청소 ");
			// cnt++;
//			 print();
			go(nx, ny, d, 0);

		}
		// case 2-2
		// 왼쪽 방향에 청소할 방향이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
		else if (map[nx][ny] == CLEAN || map[nx][ny] == WALL) {
			// System.out.println("case2-2 ");
			count++; // 방향 카운트 증가
			if (count <= 3) { // 네 방향 회전 전이라면 방향회전후 2로 돌아간다.
				d = d + 1;
				if (d == 4)
					d = 0;
				// System.out.println("d 만 회전 :" + d);
				go(i, j, d, count);
			} else { // 네 방향 회전 한 경우
				// 2-3 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고
				// 2번으로 돌아간다.
				// 원래 방향 보도록 바꿔준다.
				d = d + 1;
				if (d == 4)
					d = 0;
				int bx = i - dx[d];
				int by = j - dy[d];
				// System.out.println("현재 좌표 " + i + "," + j + " 에서 " + d + "
				// 방향으로 후진 : " + bx + "," + by);
				if (bx >= 0 && by >= 0 && bx < N && by < M) {
					if (map[bx][by] == CLEAN || map[bx][by] == BLANK) {
						// System.out.println(bx+" ,"+by +" 로 후진 ! ==========");
						go(bx, by, d, 0);
					}
				}

			}
		}
		return;
	}
}
