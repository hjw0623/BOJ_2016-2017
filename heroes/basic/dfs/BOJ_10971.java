package heroes.basic.dfs;

import java.util.Arrays;
import java.util.Scanner;

//외판원 순회 2
/*
 1번부터 N번까지 번호가 매겨져 있는 도시들이 있고, 도시들 사이에는 길이 있다. (길이 없을 수도 있다) 
 이제 한 외판원이 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획하려고 한다.
 단, 한번 갔던 도시로는 다시 갈 수 없다. (맨 마지막에 여행을 출발했던 도시로 돌아오는 것은 예외)
 이런 여행 경로는 여러 가지가 있을 수 있는데, 가장 적은 비용을 들이는 여행 계획을 세우고자 한다.

 각 도시간에 이동하는데 드는 비용은 행렬 W[i][j]형태로 주어진다. 
 W[i][j]는 도시 i에서 도시 j로 가기 위한 비용을 나타낸다. 비용은 대칭적이지 않다. 
 즉, W[i][j] 는 W[j][i]와 다를 수 있다. 모든 도시간의 비용은 양의 정수이다.
 W[i][i]는 항상 0이다. 경우에 따라서 도시 i에서 도시 j로 갈 수 없는 경우도 있으며 이럴 경우 W[i][j]=0이라고 하자.

 N과 비용 행렬이 주어졌을 때, 가장 적은 비용을 들이는 외판원의 순회 여행 경로를 구하는 프로그램을 작성
 */
public class BOJ_10971 {
	static int graph[][];
	static int dp[][]; // dp[current][visited] = 현재 위치가 current이고 방문했던 도시들이
						// visited일 때, 부분 경로(집합)최솟값.
	// visited는 비트마스크를 사용
	static int N;
	static final int INF = 10_000_000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		graph = new int[N + 1][N + 1];
		dp = new int[N + 1][1 << (N + 1)];

		// 0번 인덱스는 편의상 다 버린다.
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				graph[i][j] = sc.nextInt();
			}
		}
		for(int i=0; i<=N; i++){
			Arrays.fill(dp[i], -1);
		}
		//start = 1, visited = 0000 0000 0000 0001  = 1
		int ret = TSP(1, 1);
		System.out.println(ret );
	}

	// 재귀 함수의 정의
	// shortestPath(current, visited) = 현재 위치가 current 이고, 방문했던 도시들이 visited에
	// 주어질 때,
	// current에서 시작해 visited를 제외한 나머지 도시를 방문하는 부분 경로(집합)의 최솟값
	public static int TSP(int current, int visited) {
		// Basis 1 : 모든 지점을 방문한 상태라면
		if (visited == ((1 << N) - 1))
			return graph[current][1]; // current -> 1의 비용을 리턴한다

		// Basis 2 : 현재 위치로부터 visited의 조합만큼 방문한 적이 있다면 메모이제이션 값을 리턴
		if (dp[current][visited] >= 0)
			return dp[current][visited];

		// Recursive
		int ret = INF;

		// 집합에서 다음에 올 원소값을 고르자.
		// 1~N개의 도시 중 다음에 올 원소 값을 방문했는지, 경로가 있는지 체크해주고 재귀 탐색을 시도한다.
		for (int next = 1; next <= N; next++) {
			// 이미 방문했다면 패스
			if ((visited & (1 << (next - 1))) != 0) {
				continue;
			}
			if (graph[current][next] == 0)
				continue;
			// 방문으로 바꿔준다.
			visited += (1 << (next - 1));
			int recursiveMax = graph[current][next] + TSP(next, visited); //재귀 탐색 
			// 방문 체크를 해제한다
			visited -= (1 << (next - 1));
			ret = Math.min(recursiveMax, ret);
		}
		return ret;
	}
}
