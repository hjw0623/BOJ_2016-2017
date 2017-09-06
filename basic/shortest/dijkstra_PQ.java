package basic.shortest;

import java.util.PriorityQueue;

class Element implements Comparable<Element> {
	private int index;
	private int distance;

	@Override
	public int compareTo(Element o) {
		return distance <= o.distance ? -1 : 1;
	}

	Element(int index, int dist) {
		this.index = index;
		this.distance = dist;
	}

	public int getIndex() {
		return index;
	}

	public int getDistance() {
		return distance;
	}
}

public class dijkstra_PQ {
	static boolean[] visited;
	static int nV, nE;
	static int[] dist;
	static int[][] ad;
	static final int INF = 1_000_000;

	public static void djikstra(int start) {
		PriorityQueue<Element> q = new PriorityQueue<Element>();
		dist[start] = 0;// 시작지점 dist는 0이다.
		q.offer(new Element(start, dist[start]));

		while (!q.isEmpty()) {
			// PQ의 가장 dist가 낮은 값을 픽해서 cost와 현재 인덱스를 변수에 저장하고 pop한다.
			int cost = q.peek().getDistance();
			int here = q.peek().getIndex();
			q.poll();
			// 방문 체크
			visited[here] = true;

			if (cost > dist[here]) { // 현재 거리값이 dist[here]보다 크면 계산하지 않는다.
				continue;
			}

			System.out.println(here); // 현재 방문한 노드 출력

			for (int i = 0; i <= nV; i++) {
				if (ad[here][i] != 0 && dist[i] > dist[here] + ad[here][i]) {
					dist[i] = dist[here] + ad[here][i];
					q.offer(new Element(i, dist[i]));
				}
			}
		}
	}

	

	public static void main(String[] args) {

	}

}
