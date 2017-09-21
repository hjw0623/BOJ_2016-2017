package Kakao_CodeFest.solution1;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static class Point {
		public int x, y;
		public int value;
		public Point(int a, int b, int v) {
			this.x = a;
			this.y = b;
			this.value = v;
		}
	}

	public static int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		boolean visited[][] = new boolean[m][n];
		
	
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 색칠 외 영역이면 다음 원소 탐색
				if (picture[i][j] == 0)
					continue;
				// bfs 탐색 전에 이미 방문했다면 다음 원소 탐색
				if (visited[i][j])
					continue;
				
				// bfs 출발
				Queue<Point> queue = new LinkedList<>();
				int v = picture[i][j]; 
				visited[i][j] = true; //시작지점 방문 체크 
				queue.add(new Point(i, j, v));
				
				int count = 1;
				while (!queue.isEmpty()) {
					//현재 탐색 지점 
					Point cur = queue.poll();
//					System.out.println("현재 탐색 지점 좌표 :"+cur.x+" "+cur.y+ ", 색값 : "+cur.value);
					//다음 탐색 지점 
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];
						//범위 판정 
						if(nx<0|| ny<0|| nx>=m || ny>=n) continue;
						//색이 같고, 방문하지 않았다면 
						if(picture[cur.x][cur.y] == picture[nx][ny] && !visited[nx][ny]){
							visited[nx][ny] = true;
							count++;
							queue.add(new Point (nx, ny, v));
						}
					}
				}
				if(count >maxSizeOfOneArea  )
					maxSizeOfOneArea = count ;
				numberOfArea++;

			}
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	public static void main(String[] args) {

		int arr[][] = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		
		int ret[] = solution(6,4, arr);
		System.out.println(ret[0]);
		System.out.println(ret[1]);

	}

}
