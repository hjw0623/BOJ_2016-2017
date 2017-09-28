package heroes.basic.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

//토폴로지 소트 기본 구현 문제. 익숙해질 것 
//http://www.geeksforgeeks.org/topological-sorting/를 참고함 
//방향 그래프이다. 
class Graph {
	private int V; // # of vertices
	private LinkedList<Integer> adj[]; // adj List 그래프. 0번~V-1번이므로 번호조정 +1 나중에

	// constructor
	public Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList();
		}
	}

	// 간선 추가.
	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	// recursive function for topologicalSort
	void topologicalSortUtil(int v, boolean visited[], Stack stack) {
		// 현재 노드를 방문
		visited[v] = true;
		Integer i;
		// 현재 노드와 인접한 다른 노드들을 모두 탐색한다
		Iterator<Integer> it = adj[v].iterator();
		while (it.hasNext()) {
			i = it.next();		// 다음 방문 노드
			if (!visited[i]) {	// 방문하지 않았다면 재귀탐색한다.
				topologicalSortUtil(i, visited, stack);
			}
		}
		// 다음 방문할 노드가 없을 때 스택에 현재 노드를 넣는다.
		// 위상정렬 특성상 이전에 필요한 선행노드 탐색이 다 이루어져야 현재 노드를 스택에 담을 수 있다. 
		stack.push(new Integer(v));
	}

	void topologicalSort() {
		Stack stack = new Stack();
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		// 재귀 보조 함수를 호출하여 모든 vertex를 저장
		for (int i = 0; i < V; i++) {
			if (visited[i] == false) { 	//방문하지 않은 정점이라면 방문 
				topologicalSortUtil(i, visited, stack);
			}
		}
		//출력 
		while (!stack.isEmpty()) {
			int cur = (int) stack.pop();
			cur++;
			if (stack.size() >= 1) {
				System.out.print(cur + " ");
			} else {
				System.out.print(cur);
			}
		}
	}
}

public class BOJ_2252 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Graph graph = new Graph(N);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			graph.addEdge(start, end);
		}
		char arr[] = new char[10];
		System.out.println(arr[0] == '\0');
		graph.topologicalSort();
	}
}
