package heroes.intermediate.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;
//텀프로젝트
//https://www.acmicpc.net/problem/9466
/**
 
 * @author hw
 * 1. 혼자 팀
 * 2. 여러명이 팀 
 *  이 혼자 팀을 이룰 경우는 이전의 방문했기 때문에 사이클이라고 판단할 수 없게 된다.
	그렇다고 방문여부를 체크하지 않는다면, {4,5,6} 그래프의 중복 여부를 판단할 수 없게 된다.
	탐색할수록 정점의 개수(cnt)는 늘어간다.

	c 배열에 탐색할때마다 해당 정점을 기준으로 탐색된 정점의 개수를 저장한다.
	그러던 중 사이클이 존재하면, 사이클이 존재하는 정점을 인덱스로 활용하는 것이다.
	(탐색된 정점 개수 - 사이클 정점에 대한 길이)를 통해 사이클을 이루는 정점의 개수를 구하게 된다.

 */
public class BOJ_9466 {
	static int T, N;
	static int student[];
	static boolean checked[];
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int d[] = new int[N+1]; //adj 행렬 그래프 
			student = new int[N + 1];
			checked = new boolean[N + 1];
			st = new StringTokenizer(br.readLine());
			
			
			for (int n = 1; n <= N; n++) {
				student[n] = Integer.parseInt(st.nextToken());
			}
			int cycleNodeNum=0;
			for(int j=1; j<=N;j++){
				if(!checked[j]){
//					cycleNodeNum+=dfs(j, j, d, student, checked, 1);
				}
			}
//			System.out.println(solution(student)-1);
		}
	}
	
	//
	public static int dfs(int[] a, int[]c, int start[], int v, int startV){
		int cnt=1;
		
		while(true){
			if(c[v]!=0){
				if(start[v]!=startV){
					//이미 방문했고, 정점 시작점이 다른 경우 사이클이 아니다. 
					return 0;
				}
				return cnt-c[v];
			}
			start[v] = startV;
			c[v] = cnt;
			v = a[v];
			cnt++;
		}
	}
}
