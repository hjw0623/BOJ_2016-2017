package heroes.intermediate.dfs;

//역사 
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1613 {
	static int graph[][];
	static int reverse[][]; // 사건 순서 뒤집은 그래프
	static int dist[][]; // 각 i->j로의 최소비용을 저장한다.
	static int revDist[][]; // ""
	static int V, E;
	static final int INF = 9999;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		graph = new int[V + 1][V + 1];
		reverse = new int[V + 1][V + 1];
		dist = new int[V + 1][V + 1];
		revDist = new int[V + 1][V + 1];
		for (int v = 0; v <= V; v++) {
			Arrays.fill(graph[v], INF);
			Arrays.fill(reverse[v], INF);
		}
		for (int e = 0; e < E; e++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			graph[start][end] = 1;
			reverse[end][start] = 1;
		}
		FloydWarshall(graph);
		FloydWarshall2(reverse);
		// System.out.println();
		// print(dist);
		// System.out.println("====reverse below===");
		// print(revDist);
		int S = sc.nextInt();
		for (int s = 0; s < S; s++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			System.out.println(hasSequencePath(from, to));
		}
	}

	static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	// cnt는 1부터 시작한다
	public static int hasSequencePath(int from, int dest) {
		// System.out.println("dist :"+dist[from][dest] +" rev :
		// "+revDist[from][dest]);
		// from -> dest 경로 존재하면 앞 사건이 먼저인 경우
		if (dist[from][dest] < INF) {
			return -1;
		}

		// from -> dest가 없고 dest->from이 있으면 뒤 사건이 먼저인 경우다.
		else if ((dist[from][dest] == INF) && (revDist[from][dest] < INF)) {
			return 1;
		} else
			return 0; // 모르면 0
	}

	// 뒤 사건이 먼저 일어난 경우를 가정하자
	public static void FloydWarshall2(int reverse[][]) {

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				revDist[i][j] = reverse[i][j];
			}
		}
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (revDist[i][j] > revDist[i][k] + revDist[k][j]) {
						revDist[i][j] = revDist[i][k] + revDist[k][j];
					}
				}
			}
		}
	}

	public static void FloydWarshall(int[] graph[]) {

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				dist[i][j] = graph[i][j];
			}
		}
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
	}
}
