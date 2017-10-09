package heroes.intermediate.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1707_DFS {
	public static int testCase;
	public static int nV, nE;
//	public static boolean visited[];
	//미방문:0 		빨강 : 1 	파랑 : 2;
	public static int color[]=null;
	//이중 인접리스트
	public static ArrayList<ArrayList<Integer>> adjList=null;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		StringBuilder  sb = new StringBuilder (); 
		
		
		for(int k=0; k<testCase; k++){
			adjList = new ArrayList<ArrayList<Integer>> ();
			StringTokenizer st  = new StringTokenizer(br.readLine(), " ");
			
			nV = Integer.parseInt(st.nextToken());
			nE = Integer.parseInt(st.nextToken());
//			visited = new boolean[nV+1];
			color = new int [nV+1];
//			System.out.println("nV: " +nV+" nE: "+nE);
			//그래프 초기화
			for(int j=0; j<nV+1; j++){
				adjList.add(new ArrayList<Integer>());
			}
			//그래프 edge 초기화
			for(int e=0; e<nE; e++){
				StringTokenizer stt  = new StringTokenizer(br.readLine());
				int p1 = Integer.parseInt(stt.nextToken());
				int p2 = Integer.parseInt(stt.nextToken());
//				System.out.println("p1 : "+p1+" p2: "+p2);
				adjList.get(p1).add(p2);
				adjList.get(p2).add(p1);
			}	
			
			//dfs 탐색
			for(int u=1; u<=nV;u++){
				if(color[u]==0){
					dfs(u,1);
				}
			}
			
			// 이분 그래프인지 확인하기 위한 변수			
			 boolean flag = true;
			 breakOut: for(int u=1; u<=nV;u++){
				 for(int v : adjList.get(u)){
					 if(color[u]==color[v]){
						 flag =false;
						 break breakOut;
					 }
				 }
			 }
			 
			if(flag){
				sb.append("YES");
			}else{
				sb.append("NO");
			}sb.append("\n");
		}	
		System.out.println(sb.toString());
	}
	//color를 파라미터로 전달해줘서 재귀적으로 반대 색을 칠한다.
	public static void dfs(int vertex, int c){
		//색칠 안 되있으면 탐색해서 색 c를 넣는다.
		color[vertex] = c;
		for(int a : adjList.get(vertex)){
			//인접 노드가 미방문상태이면 방문		
			if(color[a]==0 ){
				// 출발 노드가 빨강(1) -> 도착 노드 파랑(2 = 3 - 1)
				// 출발 노드가 파랑(2) -> 도착 노드 빨강(1 = 3 - 2)
				// 따라서, 다음 색상은 3 - c
				dfs(a, 3-c);
			}
		}
	}
}
