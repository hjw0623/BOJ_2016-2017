package basic.shortest;

import java.util.ArrayList;
import java.util.Scanner;
//가중치 없는 양방향 인접리스트 그래프 
public class basic_doublyLLgraph {
	static int nV;
	static int nE;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		nV = sc.nextInt();
		nE = sc.nextInt();
		ArrayList<ArrayList<Integer>> adjGraph = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < nV; i++) {
			adjGraph.add(new ArrayList<Integer>()); //adjGraph에 담을 리스트 초기화
			
		}
		for (int i=0; i<nE; i++){
			int t1, t2;
			t1 = sc.nextInt();
			t2 = sc.nextInt();
			adjGraph.get(t1).add(t2); //양방향이므로 
			adjGraph.get(t2).add(t1);

		}
	}
}
