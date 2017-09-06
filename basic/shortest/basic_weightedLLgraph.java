package basic.shortest;

import java.util.ArrayList;
import java.util.Scanner;

//Edge를 하나의 클래스로 만든다.
class Edge<W, V>{
	private W weight;
	private V v;
	public void set_Edge(W weight, V v){
		this.weight = weight;
		this.v = v;
	}
}
public class basic_weightedLLgraph {
	static int nV, nE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		nV = sc.nextInt();
		nE = sc.nextInt();
		
		ArrayList<Edge> graph = new ArrayList<Edge>();
		Edge<Integer, Integer> edge = new Edge<Integer, Integer>();
		for(int i=0; i<nV; i++){
			graph.add(new Edge<Integer, Integer>()); //Edge 초기화, 메모리 할당 
		}
		for(int i=0; i<nE; i++){
			int t1 = sc.nextInt();  	//시작 v
			int t2 = sc.nextInt();  	//도착 v
			int weight = sc.nextInt(); 	//weight
			
			graph.get(t1).set_Edge(t2, weight); //단방향
			graph.get(t2).set_Edge(t1, weight); //양방향 
		}
	}

}
