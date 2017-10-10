package heroes.snu.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14699 {

	static int graph[][];
	static int height2[];
	static boolean visited[];
	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		height2 = new int[V + 1];
		graph = new int[V + 1][V + 1];
		visited = new boolean[V + 1];
		dp = new int[V + 1];
		st = new StringTokenizer(br.readLine());
		int high;
		for (int i = 1; i <= V; i++) {
			high = Integer.parseInt(st.nextToken());
			height2[i] = high;
		}
		Arrays.fill(dp, 1);
		for (int e = 1; e <= E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from][to] = 1;
		}

		for (int v = 1; v <= V; v++) {
			if (dp[v] > 1)
				continue;
			dfs(v);
			Arrays.fill(visited, false);
		}
		for (int i = 1; i <= V; i++) {
			System.out.println(dp[i]);
		}
	}

	// 현재 쉼터와 현재까지 지나온 쉼터 개수
	public static int dfs(int current) {
		// 이미 방문한 쉼터이면 나온다.
		if (visited[current])
			return 1;
		if (dp[current] != 1) {
			// System.out.println(" memo current: "+current+ " 에 저장된 값 :
			// "+dp[current]);
			return dp[current];
		}
		visited[current] = true;
		for (int next = 1; next < height2.length; next++) {
			int tmp = 1;
			if (graph[current][next] == 1 || graph[next][current] == 1) {
				// 현재 위치보다 더 높은 곳이고, 방문하지 않았다면 방문
				if (height2[next] > height2[current]) {
					if (!visited[next]) {
						tmp = dfs(next) + 1; // next재귀의 값에 +1한 값이 current 값
						// System.out.println(next+" 탐색 결과 :"+tmp+ ", 현재 current
						// dp : "+dp[current]);
						visited[next] = false; // 원복시켜준다.

						if (tmp > dp[current]) {
							// System.out.println(current+"에 "+tmp +"저장 ");
							dp[current] = tmp;
						}
					}
				}
			}

		}
		return dp[current];
		// return ret;
	}
}
