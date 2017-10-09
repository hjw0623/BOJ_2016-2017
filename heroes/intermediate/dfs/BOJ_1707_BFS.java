package heroes.intermediate.dfs;

//이분그래프 
//https://www.acmicpc.net/problem/1707
//http://www.geeksforgeeks.org/bipartite-graph/
/**
 * == 이분그래프 판별 == 모든 (u, v) edge에 대해 u가 U에 속해있고 v가 V에 속해있거나, u가 V에 속하고 v가
 * U에속해있어야 한다. 같은 집합 내 정점끼리 연결한 간선이 없어야 한다.
 * 
 * 두 색을 이용하여 집합내의 정점의 색을 같게 만든다. 사이클 그래프도 이분그래프가 될 수 있다. (짝수인 경우에만 가능)
 * 
 * == BFS를 이용하여 그래프의 이분그래프 여부를 판별 == 1. 출발 정점에 RED 할당. (집합U에 넣는다.) 2. 인접 정점은
 * BLUE에 할당. (집합 V에 넣는다.) 3. 인접정점의 인접정점을 모두 RED로 칠한다. (U에 넣음) 4. 모든 정점에 대해 이전
 * 정점의 반대 색을 넣어준다. 5. 색칠 과정중에 현재 정점이 인접 정점과 같은 색이라면 그래프는 이분그래프가 아니다.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1707_BFS {

	// static ArrayList<Edge> list = new ArrayList<>();
	static ArrayList< ArrayList<Integer>> graph;
	static int N, E;
	static final int RED = 1;
	static final int BLUE = -1;
	static final int NOTVISITED= 0;
	static int colorArr[] ;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			// 그래프 초기화 
			graph = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i <= N; i++) {
				graph.add( new ArrayList<Integer>());
				
			}
			colorArr = new int [N+1];
			Arrays.fill(colorArr, 0);
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) ;
				int to = Integer.parseInt(st.nextToken());
				graph.get(from).add(to);
				graph.get(to).add(from);
			}
			
			//색상 초기화 
			boolean isBi =true;
			Queue<Integer> queue = new LinkedList<>();
			for(int i = 1; i<=N; i++){
				if(colorArr[i]==0){
					queue.add(i);
					colorArr[i] = RED;
				}
				while(!queue.isEmpty()&&isBi ){
					int u = queue.poll();
					for(int v : graph.get(u)){ //인접 노드 탐색 
						if(colorArr[v] ==0){
							queue.add(v);
							colorArr[v]  = colorArr[u]*-1;
							
						}
						else if(colorArr[u]+colorArr[v]!=0){
							isBi = false;
							break;
						}
					}
				}
			}

			System.out.println(isBi? "YES":"NO");
		}
	}
//	static boolean isBipartite(){
		
//		Queue<Integer> queue = new LinkedList<>();
//		for(int i = 1; i<=N; i++){
//			if(colorArr[i]==0){
//				queue.add(i);
//				colorArr[i] = RED;
//			}
//			while(!queue.isEmpty()&& ){
//				int u = queue.poll();
//				for(int v : graph[u]){ //인접 노드 탐색 
//					if(colorArr[v]==0){
//						if(colorArr[u]==RED){
//							queue.add(v);
//							colorArr[u] = BLUE;
//						}else if(colorArr[u] ==BLUE){
//							queue.add(v);
//							colorArr[v] = RED;
//						}
//					}
//					else if(colorArr[u]+colorArr[v]!=0){
//						return false;
//					}
//				}
//			}
//		}
//		return true;
//	}
}
