package study.shortest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//거의 최단 경로란 최단 경로에 포함되지 않는 도로로만 이루어진 경로 중 가장 짧은 것을 말한다. 

/*
7 9
0 6
0 1 1
0 2 1
0 3 2
0 4 3
1 5 2
2 6 4
3 6 2
4 6 4
5 6 1
 */

//class Edge implements Comparable<Edge> {
//	int vertex;
//	int distance;
//	public Edge(int v, int d){
//		this.vertex = v;
//		this.distance = d;
//	}
//	@Override
//	public int compareTo(Edge o) {
//		return this.distance < o.distance ? -1 : 1;
//	}
//}
//
public class BOJ_5719 {
//	static int nV, nE, start, end, weight;
//	static int dist[];
//	static final int INF = 1_000_000;
//	static ArrayList<ArrayList<Edge>> adj;
//	public static void di(int start, int end){
//		dist[start] = 0;
//		PriorityQueue<Edge>pq = new PriorityQueue<Edge>();
//		pq.add(new Edge(start, 0));
//		
//		while(!pq.isEmpty()){
//			int cost = pq.peek().distance;
//			int here = pq.peek().vertex;
//			pq.poll();
//			
//			// 만약 지금 꺼낸 것보다 더 짧은 경로를 알고 있다면 지금 꺼낸것을 무시한다.  
//			if(dist[here]<cost) 
//				continue;
//			
//			for(int i=0; i<adj.get(here).size(); i++){
//				int there = adj.get(here).get(i).vertex;
//				int nextDist = cost+ adj.get(here).get(i).distance;
//				
//				if(dist[there] > nextDist){
//					dist[there] = nextDist;
//					pq.add(new Edge(there, nextDist));
//				}
//			}	
//		}
//	}
//	public static void eraseShortestPath(){
//		Queue<Edge>queue = new LinkedList<Edge>();
//		queue.add(new Edge(end, dist[end]));
//		while(!queue.isEmpty()){
//			int cur = queue.peek().vertex;
//			queue.poll();
//			for(int i=0; i<adj.get(cur).size(); i++){
//				System.out.println("i :"+i+ "dist[i] : "+ dist[i]+" cur: "+cur +" dist[cur]"+dist[cur]+ " ");
//				if(dist[cur] == dist[i]+adj.get(cur).get(i).distance && adj.get(i).get(cur).distance != -1){
//					adj.get(i).get(cur).distance = -1;
//					queue.add(new Edge(i, dist[i]));
//				}
//			}
//		}
//	}
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////		while (true) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			nV = Integer.parseInt(st.nextToken());
//			nE = Integer.parseInt(st.nextToken());
////			if (nV == 0 && nE == 0) {
////				break;
////			}
//			
////			adj = new int[nV + 1][nV + 1];
//			dist = new int[nV+1];
//			Arrays.fill(dist, INF);
//			adj = new ArrayList<ArrayList<Edge>>();
//			
//			st = new StringTokenizer(br.readLine());
//			start = Integer.parseInt(st.nextToken());
//			end = Integer.parseInt(st.nextToken());
//			for(int i =0; i<nV; i++){
//				adj.add(new ArrayList<Edge>());
//			}
//			//make graph
//			for (int i = 0; i < nE; i++) {
//				st = new StringTokenizer(br.readLine());
//				int cur = Integer.parseInt(st.nextToken());
//				int next = Integer.parseInt(st.nextToken());
//				int weight = Integer.parseInt(st.nextToken());
//				adj.get(cur).add(new Edge(next, weight));
//			}
//			
//			di(start, end);
//			eraseShortestPath();
//			di(start, end);
//			System.out.println(dist[end]);
////		}
//	}

}
