package Baekjoon_2017_last_dfs_4_5;

import java.util.Scanner;

//https://www.acmicpc.net/problem/5014
//스타트링크
public class dfs_5014 {
	static int F, S, G, U, D;
	static	boolean visit[]; 
	static int d[];
	static final int MAX = 1_000_010;
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		//빌딩 총 높이
		F = sc.nextInt();
		//현재 위치
		S = sc.nextInt();
		//목표
		G = sc.nextInt();
		//
		U = sc.nextInt();
		//
		D = sc.nextInt();
		visit = new boolean[F+1];
		d = new int [F+1];
		int result = dfs(0, F, S,G, U, D);
		if (result<1_000_000){
			System.out.println(result);
		}else{
			System.out.println("use the stairs");
		}
	}
	public static int dfs(int distance, int total, int current, int dest, int up, int down){
		//도달하면 거리 리턴
		if(current==dest) return d[current]=distance;
		//범위 초과시 큰 값 리턴
		if(current>total) return 1_000_010;
		if(current<1) return 1_000_010;
		if(d[current]!=0)return d[current];
		//범위 내 재귀
		int Uptmp=MAX, Downtmp=MAX ;
		if(current<=total && current >=1 && current!=dest && !visit[current]){
			visit[current] = true;
			if(up!=0){
			Uptmp = dfs(distance+1, total, current+up, dest, up, down);
			}else{
				Uptmp =1_000_010;
			}
			if(down!=0){
			Downtmp = dfs(distance+1, total, current-down, dest, up, down);
			}else{
				Downtmp =1_000_010;
			}
			
		}		
		return 	(Downtmp>Uptmp)? Uptmp:Downtmp;

	}
}
