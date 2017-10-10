package heroes.snu.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/14711
//타일뒤집기 Easy
//특징 
//1. < 맨 윗 줄~ 2/N > 과 < N/2+1 ~ 맨 아랫줄 >은 대칭
//0,0== N-1, N-1, (0,N-1) == (N-1, 0)
//2. 검은색은 짝수개씩 붙어있다.
//3. 최초 i,j이 검은색이라면 인접한 검은색은 짝수개이다. 
//4. 처음과 마지막 줄은 고정되어있다. 

//흰색이든, 검은색이든 인접한 검은색은 짝수개여야 한다.  

public class BOJ_14711 {
//	static final int WHITE = 0;
//	static final int BLACK = 1;
	static char graph[][];
	// 상 좌 우 검색
	static int dy[] = { -1, 1, 0 };
	static int dx[] = { 0, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new char[N][N];
		String tmp = br.readLine();

		for (int t = 0; t < tmp.length(); t++) {
			graph[0][t] = tmp.charAt(t);
		}
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N; j++) {
//				System.out.println("i, j " + i + "," + j);
				int cnt = 0; // 인접한 검은색을 센다.
				for (int k = 0; k < 3; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;
					if (graph[nx][ny] == '#') //black
						cnt++;
				}
//				System.out.println("cnt : "+cnt);
				// 현재까지 인접한 검은색이 짝수개면 아래방향에 흰색을 놓는다.
				if (cnt % 2 == 0) {
					graph[i + 1][j] = '.';
				} else {
					graph[i + 1][j] = '#';
				}
			}
		}
		print(graph);
		br.close();
	}

	static void print( char arr[][]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}
