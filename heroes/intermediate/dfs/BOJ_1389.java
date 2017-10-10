package heroes.intermediate.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//Floyd-Warshall 
//http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
//http://www.crocus.co.kr/536
//플로이드 워셜 알고리즘은 모든 정점에 대해 모든 다른 정점에 대한 최단 경로를 다 구해준다.
//그래프에서 모든 꼭짓점 사이의 최단 경로의 거리를 구하는 알고리즘이다. 음수 가중치를 갖는 간선도 순환만 없다면 잘 처리된다

public class BOJ_1389 {
	static int N;
	static int M;
	final static int INF = 9999;

	static int graph[][];
	// 거리를 저장
	static int dist[][];
	// 직전 정점을 저장 (초기값은 graph와 같다 )

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		//edge가 없는 부분은 INF로 초기화 해준다. 
		for(int i=0; i<N; i++){
			Arrays.fill(graph[i], INF);
		}
		int start, end;
		//나머지 간선의 경로를 입력 
		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken()) - 1;
			end = Integer.parseInt(st.nextToken()) - 1;
			graph[start][end] = 1;
			graph[end][start] = 1;
		}
		floydWarshall(graph);
//		print(graph);
//		System.out.println("===");
//		print(dist);
		int ret[] = new int [N];
		int retIdx = 0;
		int retVal = INF;
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				if(i==j) continue;
				ret[i] +=dist[i][j];
			}
			if(retVal > ret[i]){ //작은 번호부터 검사한다.동률을 배제하면 큰 번호로 갱신없음 
				retIdx = i;
				retVal = ret[i];
			}
		}
		System.out.println(retIdx+1);
	}

	static void print(int  arr[][]) {
		for (int i = 0; i < N; i++) {
			for(int j=0; j<N; j++){
				System.out.print(arr[i][j]+ " ");
			}System.out.println();
		}
	}

	static void floydWarshall(int graph[][]) {
		dist = new int[N][N];
		int i, j, k;
		// 처음 최단 경로 값은 graph의 입력값과 같다
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				dist[i][j] = graph[i][j];
			}
		}
		//3중 for문으로 최단경로 개선 V^3 
		for (k = 0; k < N; k++) {
			for (i = 0; i < N; i++) {
				for (j = 0; j < N; j++) {
					if(i==j) continue;
					if(dist[i][j] > dist[i][k]+dist[k][j] )//현재 i~j보 i~k+k~j가 더 작으면 갱신 
						dist[i][j] = dist[i][k]+dist[k][j];
				}
			}
		}
	}
}
