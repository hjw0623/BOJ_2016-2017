package study.shortest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Element implements Comparable<Element> {
	int vertex;
	int distance;

	public Element(int v, int d) {
		this.vertex = v;
		this.distance = d;
	}

	@Override
	public int compareTo(Element o) {
		return this.distance < o.distance ? -1 : 1;
	}
}

public class BOJ_5719_re {
	// N vertext, M edge, S start, D destination
	static int N, M, S, D;
	static int graph[][]; // = new int[501][501];
	static int dist[];
	static final int INF = 1_000_000;

	public static void djikstra(int start, int end) {
		dist[start] = 0; // 시작지점 비용은 0

		PriorityQueue<Element> pq = new PriorityQueue<Element>();
		pq.add(new Element(start, 0));

		while (!pq.isEmpty()) {
			int here = pq.peek().vertex;
			int curCost = pq.peek().distance;
			pq.poll();
			// 현재 저장된 거리비용이 queue에서 꺼낸 값보다 작다면, 계산하지 않고 다음 queue 요소로 넘어간다.
			if (dist[here] < curCost)
				continue;

			for (int i = 0; i < N; i++) {
				int there = i;
				int nextCost = curCost + graph[here][i];
				if (graph[here][i] != 0 && dist[there] > nextCost) {
					dist[there] = nextCost; // 갱신
					pq.add(new Element(there, nextCost));
				}
			}
		}
	}

	public static void eraseShortestPath(int dest) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(dest);

		while (!queue.isEmpty()) {
			int endest = queue.poll(); // 직전 vertex
			for (int i = 0; i < N; i++) {
				if (graph[i][endest] != 0 && dist[endest] == dist[i] + graph[i][endest]) {
					graph[i][endest] = INF;
					queue.add(i);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0)
				break;

			dist = new int[N];
			graph = new int[N][N];
			Arrays.fill(dist, INF);

			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				graph[start][end] = weight;
			}

			djikstra(S, D);
			// System.out.println(dist[D]);
			// printGraphWeight();
			eraseShortestPath(D);
			// printGraphWeight();

			Arrays.fill(dist, INF);
			djikstra(S, D);
			if (dist[D] < INF) {
				System.out.println(dist[D]);
			} else {
				System.out.println(-1);
			}
		}
	}

	static void printGraphWeight() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
