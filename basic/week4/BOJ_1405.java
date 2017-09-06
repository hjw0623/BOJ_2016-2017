package basic.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2 25 25 25 25
// 0.75
// 2<= N <=14

public class BOJ_1405 {
	static boolean visited[][] = new boolean[30][30];
	static double prob[] = new double[4];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	
	static int N;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		//EE, WW, SS, NN
		for(int i=0; i<4; i++){
			prob[i] = (double)(Double.parseDouble(st.nextToken())/100); 
		}
		double ret = backtracking(N, 15, 15);
		System.out.println(ret);
	}
	
	public static double backtracking(int num,  int x, int y ){
		//basis 1. 현재 경로가 '단순'하지 않다면 0.0반환 
		if(visited[x][y]){ 
			return 0.0;
		}
		//basis 2. 현재 경로가 단순하다면 1.0  반환.
		if(num ==0 && !visited[x][y]){ //
			return 1.0; 
		}
		//방문체크 
		visited[x][y] = true;
		double result = 0.0;
		//4 방향에 대해서 백트래킹을 시도한 후, 각각의 확률을 곱한다. 
		for(int i=0; i<4; i++){
			result += prob[i]* backtracking(num-1, x+dx[i], y+dy[i] );
		}
		visited[x][y] = false;
		
		return result;
	}

}
