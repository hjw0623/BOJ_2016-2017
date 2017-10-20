package heroes.intermediate.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1
9 8
1 2
1 3
2 4
2 5
3 5
7 8
7 9
8 9
NO
 */
public class Main {

	// static ArrayList<Edge> list = new ArrayList<>();
	static ArrayList<Integer> graph[];
	static int N, E;
	static final int RED = 1;
	static final int BLUE = 0;
	static final int NOTVISITED= -1;
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
			graph = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				graph[from].add(to);
				graph[to].add(from);
			}
			//색상 초기화 
            colorArr = new int[N];	
            Arrays.fill(colorArr, -1);  
            
        	boolean isBi = isBipartite(0);
			if (isBi)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	static boolean isBipartite(int src){
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);
		colorArr[src] = RED; //1
		while(!queue.isEmpty()){
			int u = queue.poll();
//			System.out.println("u : "+u);
			for(int v =0; v<graph[u].size(); v++){
				if(graph[u].get(v)==u){ //self loop
					System.out.println("self loop");
					return false;
				}
				//미방문 
				if(colorArr[graph[u].get(v)]==NOTVISITED){
					colorArr[graph[u].get(v)] = 1-colorArr[u]; 	//u가 RED면 BLUE가 되겠다.  
//					System.out.println("visit "+v+" color of v : "+colorArr[graph[u].get(v)]+ "color of u "+colorArr[u]);

					queue.add(graph[u].get(v));
				}else if(colorArr[graph[u].get(v)]==colorArr[u]){
//					System.out.println("visit "+v+" color of v : "+colorArr[graph[u].get(v)]+ "color of u "+colorArr[u]);

					return false;
				}
			}
		}
		return true;
	}
}