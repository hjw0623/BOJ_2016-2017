package heroes.intermediate.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2468 {
	static int N;
	static int map[][];
	static boolean check[][];
	static int cnt = 0;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		check = new boolean[N][N];
		int lowest = 100;
		int highest = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (highest < map[i][j]) {
					highest = map[i][j];
				}
				if (lowest > map[i][j]) {
					lowest = map[i][j];
				}
			}
		}
		int result =1;
		for (int lev = lowest; lev < highest; lev++) {
			clearCheck();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] <= lev)
						continue;
					if(check[i][j])
						continue;
					dfs(i, j, lev);
					cnt++;
				}
			}
			if(result<cnt){
				result = cnt;
			}
			cnt = 0;
		}
		System.out.println(result);
	}
	static void clearCheck(){
		for(int i=0; i<N; i++){
			Arrays.fill(check[i], false);
		}
	}

	static int dfs(int x, int y, int level) {
		if (map[x][y] <= level) // 막힌 곳
			return 0;
		if (check[x][y]) // 이미 방문함
			return map[x][y];
		else {
			check[x][y] = true;
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				dfs(nx, ny, level);
			}
		}
		return map[x][y];
	}

}
