package heroes.snu.problem;

import java.util.HashSet;
import java.util.Scanner;

public class BOJ_14709 {
	static int graph[][] = new int [6][6];
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		int N = sc.nextInt();
		for(int n=1; n<=N; n++){
			int start = sc.nextInt();
			int end = sc.nextInt();
			graph[start][end] = 1;
			graph[end][start] = 1;
		}
		if(isFox()){
			System.out.println("Wa-pa-pa-pa-pa-pa-pow!");
		}else{
			System.out.println("Woof-meow-tweet-squeek");
		}
	}
	static boolean isFox(){
		HashSet<Integer> set = new HashSet<>();
	
		for(int i=1; i<=5; i++ ){
			for(int j=1; j<=5; j++){
				if(graph[i][j] ==1){
					set.add(i);
					set.add(j);
				}
			}
		}
		if(set.size()==3){
			if(set.contains(1) && set.contains(3) && set.contains(4)){
				if(graph[1][3]==1 && graph[3][4]==1 && graph[1][4]==1){
					return true;
				}
			}
		}
		return false;
	}
}
