package basic.shortest;
//http://wisedev.kr/86

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Element2 implements Comparable<Element2> {
	int distance;
	int vertex;

	Element2(int v, int d) {
		this.vertex = v;
		this.distance = d;
	}

	@Override
	public int compareTo(Element2 o) {
		return this.distance < o.distance ? -1 : 1;
	}
}

public class djikstra_PQ2 {
	public static final int INF = 1000_000;
	static int nV, nE;
	static int dist[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		nV = Integer.parseInt(st.nextToken());
		nE = Integer.parseInt(st.nextToken());
		int startV = Integer.parseInt(br.readLine());
		
		
		//dist배열 초기화 
		dist = new int [nV+1];
		for(int i =1; i<=nV; i++){
			dist[i] = INF;
		}
		//연결리스트로 그래프 표기.  
		ArrayList<ArrayList<Element2>> adj = new ArrayList<ArrayList<Element2>>();
		for(int i=0; i<=nV; i++){
			adj.add(new ArrayList<Element2>());
		}
		for(int i=0; i<nE; i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj.get(start).add(new Element2(end, weight));
		}
		
		
		//dijkstra 시작 
		PriorityQueue<Element2> pq = new PriorityQueue<Element2>();

		dist[startV]  = 0;
		pq.offer(new Element2(startV, 0));
		
		while(!pq.isEmpty()){
			int here = pq.peek().vertex;
			int cost = pq.peek().distance;
			pq.poll();
			
			// 만약 지금 꺼낸 것보다 더 짧은 경로를 알고 있다면 지금 꺼낸것을 무시
			if(dist[here] < cost)
				continue;
			
			// 인접 경로 모두 검사 
			for(int i=0; i< adj.get(here).size(); i++){
				int there = adj.get(here).get(i).vertex;
				int nextDist = cost + adj.get(here).get(i).distance;
				// 더 짧은 경로 발견시 dist 갱신 
				// dist 벡터에는 시작점 -> there 위치까지의 최단 거리가 담겨있다.
				if(dist[there] > nextDist){
					dist[there] = nextDist;
					pq.add(new Element2(there, nextDist));
				}
			}
		} //end of dijkstra
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<dist.length; i++){
			if(dist[i]==INF)
				sb.append("INF\n");
			else{
				sb.append(dist[i]+"\n");
			}
		}
		System.out.println(sb.toString().trim());
	}

}
