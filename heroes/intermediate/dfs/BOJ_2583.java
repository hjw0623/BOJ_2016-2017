package heroes.intermediate.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/2583
//영역 구하기
public class BOJ_2583 {
	static int M, N, K;
	static int map[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		int ldX, ldY, ruX, ruY;
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			ldX = Integer.parseInt(st.nextToken());
			ldY = Integer.parseInt(st.nextToken());
			ruX = Integer.parseInt(st.nextToken());
			ruY = Integer.parseInt(st.nextToken());
			for (int i = ldX; i < ruX; i++) {
				for (int j = ldY; j < ruY; j++) {
					map[i][j] = -1;
				}
			}
		}
//		print();
		ArrayList<Integer>list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==0){
					num++;
					dfs(i,j);
					list.add(cnt);
					cnt = 0;
				}
				if(map[i][j]==-1)continue;
				else if(map[i][j]>=1)continue;
			}
		}
		System.out.println(num);
		Collections.sort(list);
		for(int i=0; i<list.size(); i++){
			System.out.print(list.get(i)+" ");
		}
	}
	static int num=0;
	static int cnt =0;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static int dfs(int x, int y) {
		if (map[x][y] == -1) //막힌 곳 
			return 0;
		if(map[x][y]!=0) //이미 방문함 
			return map[x][y];
		else{
			
			map[x][y] = ++cnt;
			for(int k=0; k<4; k++){
				int nx = x+dx[k];
				int ny = y+dy[k];
				if(nx<0 || ny<0 || nx>=M || ny>=N)
					continue;
				dfs(nx, ny);
			}
		}
		return map[x][y];
	}

	static void print() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
