package basic.graphMST;

public class Prim {
	public static int V;
	//mstSet과 비교하여 가장 작은 간선부터 연결하는 메소드
	int minKey ( int key[], boolean mstSet[]){
		int min  = Integer.MAX_VALUE;
		int minIndex = 01;
		for(int v = 0; v<V; v++){
			//mstSet[v]==true면 사이
			if(mstSet[v] ==false && key[v] < min){
				min = key[v] ;
				minIndex = v;
				
			}
		}
		return minIndex;
	}
	//MST의 최소 가중치 총합을 리턴한다 
	public int primMST(int graph[][]){
		int parent[] = new int [V];
		int key[] = new int [V];
		boolean mstSet[] = new boolean [V];
		
		for(int i=0; i<V; i++){
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}
		//첫 노드(vertex 0)을 mst에 포함시킴. 
		key[0] =0;
		
		parent[0] = -1;
		
		for(int i=0; i<V-1; i++){
			int u = minKey(key, mstSet);
			mstSet[u] =true; //vertex u를 mst에 포함시킴 
			for(int v = 0; v<V; v++){
				//edge가 존재하고, 사이클이 아니고, edge의 가중치가 현재 key[v]보다 작다면 갱신한다 
				if(graph[u][v] !=0 && mstSet[v] ==false && graph[u][v] <key[v]){
					parent[v] = u;
					key[v] = graph[u][v];
				}
			}
		}
		int ret =0;
		for(int i=0; i<key.length; i++){
			ret += key[i];
		}
		return ret;
	}
}
