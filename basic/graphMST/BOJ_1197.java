package basic.graphMST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1197 {
	public static int V, E;
	public static int graph[][];

	public static int minKey(long key[], Boolean mstSet[]) {
		long min = Integer.MAX_VALUE;
		int min_index = -1;
		for (int v = 0; v < V; v++) {
			if (mstSet[v] == false && key[v] < min) {
				min = key[v];
				min_index = v;
			}
		}
		return min_index;
	}

	public void pirntMST(int parent[], int n, int graph[][]) {
		System.out.println("Edge weight");
		for (int i = 1; i < V; i++) {
			System.out.println(parent[i] + " - " + i + "  " + graph[i][parent[i]]);
		}
	}

	// key의 합을 리턴하도록 한다.
	public static int primMST(int graph[][]) {
		// MST에 저장할 배열
		int parent[] = new int[V];
		// 최소 가중치로 선택된 키값
		long key[] = new long[V];
		// 아직 mst에 들어오지 않은 vertex set
		Boolean mstSet[] = new Boolean[V];

		// 초기설정
		for (int i = 0; i < V; i++) {
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}
		// 첫 노드를 항상 MST에 넣자. 이떄 이 vertex의 키는 항상 '0'이다.
		key[0] = 0;
		parent[0] = -1; // 첫노드의 루트는 항상 mst의 루트.

		for (int count = 0; count < V - 1; count++) {
			// MST에 아직 미포함된 vertices 집합 중 최솟값을 지닌 vertex를 고른다.
			int u = minKey(key, mstSet);
			mstSet[u] = true; // add MST set

			for (int v = 0; v < V; v++) {
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
					parent[v] = u; // v의 부모노드로 u를 설정.
					key[v] = graph[u][v]; // key[v]를 u->v 가중치로 갱
				}
			}
		}
		
		int mstWeight = 0;
		for (int i = 0; i < key.length; i++) {
			mstWeight += key[i];
		}
		return mstWeight;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new int[V ][V ];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken())-1;
			int dest = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			graph[src][dest] = weight;
		}
		System.out.println(primMST(graph));
	}

}
