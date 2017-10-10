package heroes.intermediate.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//텀프로젝트
//https://www.acmicpc.net/problem/9466
/**
 * @author hw 1. 혼자 팀 2. 여러명이 팀
 * 
 *         http://blog.naver.com/PostView.nhn?blogId=kks227&logNo=220785731077
 * 
 *         문제 해설* 그래프에서는 문제 조건상 반드시 정점 하나당 indegree가 1일 수밖에 없기 때문에, 발생하는 컴포넌트는
 *         여러 개일지언정 각 컴포넌트는 반드시 하나의 형태를 띄게 됩니다. 가장 안쪽에 단 하나의 싸이클이 있고, 그 외 이 싸이클
 *         정점들 중 하나를 가리키는 곁가지 정점들. 곁가지 정점들의 개수 총합이 문제의 답이 됩니다. 어떤 컴포넌트는 곁가지 정점이
 *         없을 수도 있습니다.
 * 
 *         이런 문제에서 DFS로 싸이클에 속한 정점을 걸러낼 수가 있습니다. 여기서는 정점 방문을 시작했는지를 나타내는 visited
 *         배열 말고도, 그 정점의 방문함수가 완전히 끝났는지를 나타내는 finished 배열이 하나 더 필요합니다. 예를 들어서, 위
 *         예제 그래프에서 1번 정점 방문을 시작했다면 그 순간 visited[1]=true가 됩니다. 그러나 아직
 *         finished[1]은 false입니다. 인접한 정점인 3번 정점 방문을 완료하고 다 돌아와서야
 *         finished[1]=true가 됩니다.
 * 
 *         이제 우리는 DFS를 하다가, 싸이클이 발생하는 조건을 찾아야 합니다. 그 조건은 바로... 다음에 방문할 정점이 k일 때,
 *         visited[k]=true고 finished[k]=false인 경우입니다. 이 말은 무엇이냐면 인접한 정점은 이미 방문은
 *         시작했지만 그 정점의 DFS는 아직 끝나지 않았다... 즉, 그 정점에 인접한 정점, 그리고 그 정점에 인접한 정점...들의
 *         탐색이 아직 안 끝났다는 말입니다. 현재 정점이 p고 다음에 마주친 이러한 정점이 q면 q~p까지 속한 모든 정점이 하나의
 *         싸이클을 이루게 됩니다.
 * 
 *         visited[next]= false visited[next]=true finished[next] = false 아직 방문
 *         x 현재 정점과 next가 한 사이클 내에 속함
 * 
 *         finished[next] = true 존재하지 않는 경우 모든 탐색이 끝난 정점 현재 정점은 next의 사이클여부와
 *         무관하게 사이클에 없음.
 * 
 */
/*
2
7
3 1 3 7 3 4 6
8
1 2 3 4 5 6 7 8
*/
public class BOJ_9466_RE {
	static int T, N, cnt;
	static int student[];
	static boolean checked[];	//정점 방문 체크 
	static boolean finished[];	//정점의 방문 함수가 완전히 끝났는지 나타냄 
	//ex 1번 정점 방문시 checked[1]=true, finished[1]=false인 상태.
	//인접 정점인 3의 방문을 마치고 다 돌아와서야 finished[1] = true가 된다. 
	//cycle은 다음 방문 정점이 k일때 visited[k] = true && finished[k]=false 인 경우다.
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			student = new int[N + 1];
			checked = new boolean[N + 1];
			finished = new boolean[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				student[n] = Integer.parseInt(st.nextToken());
			}
//			for(int i=1; i<=N; i++){
//				System.out.print(student[i]+" ");
//			}
//			System.out.println();
			cnt = 0;
			for(int i=1; i<=N; i++){
				if(!checked[i])
					dfs(i);
			}
			System.out.println(student.length-1-cnt);
		}
	}
	
	static void dfs(int cur){
//		System.out.println(cur);
		checked[cur] = true; //방문 체크 
		int next = student[cur];
		if(checked[next]){	//방문한 곳 이라면 
			if(!finished[next]){	//사이클이 존재한다면 
				for(int k =next; k!=cur; k=student[k]){	//사이클을 이루는 정점을 돌며 카운트 
					cnt++;
				}
				cnt++;//자기 자신에 대한 cnt
			}	
		}else{	//방문하지 않았다면 next로 재귀 방문 
			dfs(next);
		}
		finished[cur] = true; // next로 시작되는 모든 방문을 완료한 다음 true로 
	}

}
