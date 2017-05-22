package boj;
//https://www.acmicpc.net/problem/14500
import java.util.Arrays;
//LEFT 0, UP 1, RIGHT 2, DOWN 3
import java.util.Scanner;

public class BOJ_14500 {
	static int N, M;
	static final int LEFT = 0, UP = 1, RIGHT = 2, DOWN = 3;
	static int map[][];
	static int dp[][][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		dp = new int[N][M][4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int max = 0;
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				int left = solve(i,j,0,0);
				int up = solve(i,j,0,1);
				int right = solve(i,j,0,2);
				int down = solve(i,j,0,3);
				
				max=max>left? max:left;
				max=max>right? max:right;
				max=max>up? max:up;
				max = max>down? max:down;
			}
		}
		System.out.println(max);
	}

	// left up right down 순
	static int dx[] = { 0, -1, 0, 1 };
	static int dy[] = { -1, 0, 1, 0 };

	public static int solve(int x, int y, int count, int dir) {
		// basis
		if (count == 3) {
			return dp[x][y][3] = map[x][y];
		}
		// 재귀 탐색
		int left = 0, right = 0, up = 0, down = 0;
		if (dir == LEFT) {
			int nextx = x + dx[LEFT];
			int nexty = y + dy[LEFT];
			if (isRange(nextx, nexty)) {
				left = solve(nextx, nexty, count + 1, LEFT);
				up = solve(nextx, nexty, count + 1, UP);
				down = solve(nextx, nexty, count + 1, DOWN);
			}
		} else if (dir == UP) {
			int nextx = x + dx[UP];
			int nexty = y + dy[UP];
			if (isRange(nextx, nexty)) {
				right = solve(nextx, nexty, count + 1, RIGHT);
				up = solve(nextx, nexty, count + 1, UP);
				left = solve(nextx, nexty, count + 1, LEFT);
			}
		} else if (dir == RIGHT) {
			int nextx = x + dx[RIGHT];
			int nexty = y + dy[RIGHT];
			if (isRange(nextx, nexty)) {
				right = solve(nextx, nexty, count + 1, RIGHT);
				up = solve(nextx, nexty, count + 1, UP);
				down = solve(nextx, nexty, count + 1, DOWN);
			}
		} else if (dir == DOWN) {
			int nextx = x + dx[DOWN];
			int nexty = y + dy[DOWN];
			if (isRange(nextx, nexty)) {
				right = solve(nextx, nexty, count + 1, RIGHT);
				left = solve(nextx, nexty, count + 1, LEFT);
				down = solve(nextx, nexty, count + 1, DOWN);
			}
		}

		int tmp = 0;
		tmp = tmp > right ? tmp : right;
		tmp = tmp > left ? tmp : left;
		tmp = tmp > up ? tmp : up;
		tmp = tmp > down ? tmp : down;
		if(count==0){
		int cross = findCross(x,y);
		// 십자모양-1개 비교
		tmp = tmp>cross? tmp:cross;
		}
		return dp[x][y][count] = tmp + map[x][y];

	}
	
	public static boolean isRange(int i, int j) {
		if (i < 0 || j < 0 || i >= N || j >= M)
			return false;
		else
			return true;
	}
	//십자모양에서 가장 작은 값 하나 뺀 ㅗ 블럭
	public static int findCross(int i, int j) {
		int cross = 0;
		int tmp[] = new int[4];

		int nx0, ny0, nx1, ny1, nx2, ny2, nx3, ny3;
		int count = 0;
		nx0 = i + dx[0];
		ny0 = j + dy[0];
		if (isRange(nx0, ny0)) {
			tmp[0] = map[nx0][ny0];
			count++;
		}
		nx1 = i + dx[1];
		ny1 = j + dy[1];
		if (isRange(nx1, ny1)) {
			tmp[1] = map[nx1][ny1];
			count++;
		}
		nx2 = i + dx[2];
		ny2 = j + dy[2];
		if (isRange(nx2, ny2)) {
			tmp[2] = map[nx2][ny2];
			count++;
		}
		nx3 = i + dx[3];
		ny3 = j + dy[3];
		if (isRange(nx3, ny3)) {
			tmp[3] = map[nx3][ny3];
			count++;
		}
		// 만약 범위 안에 있는 게 2개뿐이라면 0을 리턴
		if (count <= 2) {
			count = 0;
		} 
		else {
			Arrays.sort(tmp);
			for (int k = 1; k < 4; k++) {
				cross += tmp[k];
			}
		}
		return cross;
	}
}