package study.shortest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {
	public static boolean visited[];
	public static int graph[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int subin = Integer.parseInt(st.nextToken());
		int sister = Integer.parseInt(st.nextToken());

		 visited = new boolean[200_002];
		graph = new int[200_002];
		Arrays.fill(graph, -1);
		graph[subin] = 0;
		int result = tracking(subin, sister);
		System.out.println(result);

	}
	//BFS
	public static int tracking(int start, int dest) {

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);

		graph[start]=0;
		visited[start]=true;
		while(!queue.isEmpty()){
			int cur = queue.poll();
//			System.out.println("cur: "+cur+" graph[cur]:"+ graph[cur]);
			if(cur==dest){
				return graph[dest];
			}
			int teleport = cur*2;
			int next = cur+1;
			int past = cur-1;
			if(teleport <=100_000 && !visited[teleport]){
				graph[teleport]=graph[cur]+1;
				visited[teleport] =true;
				queue.add(teleport);
			}
			if(next <= 100_000 && !visited[next]){
				graph[next] = graph[cur]+1;
				visited[next] =true;
				queue.add(next);
			}
			if(past >=0 && !visited[past] ){
				graph[past] = graph[cur]+1;
				visited[past] = true;
				queue.add(past);
			}
		}
		return graph[dest];
	}

}
