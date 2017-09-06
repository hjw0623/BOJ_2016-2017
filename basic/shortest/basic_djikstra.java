package basic.shortest;

import java.util.Scanner;

//http://manducku.tistory.com/29
public class basic_djikstra {
	static int [][]ad; 			//각 엣지별 weight를 저장하는 영역
	static int [] dist;		  	//해당 노드까지의 최단 거리를 저장하는 배열
	static boolean []visited; 	//해당 엣지를 방문했는지 여부 체크
	static int nV, nE;
	static final int INF = 1000_000;
	
	
	public static void djikstra(int start, int end){
		dist[start] = 0;	 	// 최초 시작 값 distance 초기화
		String shortestPath="";
		
		//start지점에서 다음 방문 지점을 검사. 시작점을 제외한 nV-1 번만큼만 살펴보면 된다. 
		for(int j=0; j<nV; j++){ 
			int min = INF+1;  	// dist 최소값 찾기 위한 임시 값 초기화
			int index = -1;		// 다음에 방문할 index값을 초기화 한다.
			//모든 정점에 대해서 다음 방문 지점을 탐색하고자 한다. 
			for(int k=1; k<=nV; k++){
				//만약 현재 정점이 아직 방문하지 않았고, min값이 k정점까지의 거리보다 크다면, 
				//min을 갱신하고 다음 방문 정점을 k로 정한다. 
				if(visited[k] ==false && min > dist[k]){ 
					min = dist[k];
					index = k;
				}
			}
			visited[index] = true; //다음 방문 지점은 index값에 해당. 
			shortestPath += index +" "; //경로에 index를 추가. 
			
			//현재 방문 노드로부터 접근 가능한 노드 중에서 거리 갱신가능여부 체크 
			for(int l =1; l<=nV; l++){
				if(ad[index][l]!=0 && dist[l] > dist[index]+ad[index][l]){
					dist[l] = dist[index]+ad[index][l];
				}
			}
		}
		for(int i = 1; i <= nV; i++){
            System.out.print(dist[i]);
        }
        System.out.println("=======");
        System.out.println(shortestPath);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		nV = sc.nextInt();
		nE = sc.nextInt();
		ad = new int [nV+1][nV+1];
		dist = new int[nV+1];
		visited = new boolean[nV+1];
		
		for(int i=1; i<=nV; i++){
			dist[i] = INF;
		}
		for(int i = 0; i < nE; i++){
            int t1, t2, t3;
            t1 = sc.nextInt();
            t2 = sc.nextInt();
            t3 = sc.nextInt();
            
            ad[t1][t2] = t3;
        }
        
		djikstra(1,5);


		
	}

}
