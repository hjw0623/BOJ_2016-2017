package heroes.basic.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1937 {
	static int dp[][];
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Bamboo[][] = new int[N][N];
		dp = new int [N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				Bamboo[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result =0;
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
//				System.out.println("시작 : "+i+" "+j);
				int cur = go(i, j, Bamboo);
				if(result <cur){
					result = cur;
				}
			}
		}
		System.out.println(result);
	}

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static int go(int x, int y, int map[][]){
		//basis memo
		if(dp[x][y]!=0){
			return dp[x][y];
		}
		
		int retMax = 1;
		//recursive
		for(int k=0; k<4; k++){
			int nx = x+dx[k];
			int ny = y+dy[k];
			int tmpMax = 0;
			if(nx<0||ny<0||nx>=map.length||ny>=map.length)
				continue;
			
			else{
				//경로가 있으면 재귀 탐색 
				if(map[nx][ny] > map[x][y]){
					tmpMax = go(nx, ny, map)+1; //탐색한 재귀 깊이 만큼 +1
					retMax  = Math.max(retMax, tmpMax);
				}
			}
		}
		return dp[x][y]= retMax;
	}
}
