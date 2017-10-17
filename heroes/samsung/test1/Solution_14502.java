package heroes.samsung.test1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//4 6
//0 0 0 0 0 0
//1 0 0 0 0 2
//1 1 1 0 0 2
//0 0 0 0 0 2

//9
public class Solution_14502 {
	static int n, m;
	static int map[][];
	static int tmpMap[][];
	static int copyMap[][];
	static final int virus = 2;
	static final int blank = 0;
	static final int wall = 1;
	static int ans = 0;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point() {
		}
	}

	static ArrayList<Point> list = new ArrayList<>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		tmpMap = new int[n][m];
		// 초기 바이러스 위치 담고 있다.

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				tmpMap[i][j] = map[i][j];
				if (map[i][j] == virus) {
					list.add(new Point(i, j));
				}
			}
		}
		//wall 1
		for(int v = 0; v<n*m; v++){
			int i= (int) v/m;
			int j = v%m;		
//			System.out.println("i , j : "+i+ " , "+j);
			boolean using1 = false;
			if(tmpMap[i][j] ==blank){ //첫 벽을 세운 경우에만 2번째 포문을 돈다. 
				tmpMap[i][j] =wall;
				using1 = true;
				//wall2
				for(int v2 = v+1; v2<n*m; v2++){ 
					
					int i2= (int )v2/m;
					int j2 = v2%m;
//					System.out.println("i2 , j2 : "+i2+ " , "+j2);
					boolean using2 = false;
					
					if(tmpMap[i2][j2] ==blank){ //두번째 벽을 세운 경우에만 3번째 포문을 돈다. 
						tmpMap[i2][j2]= wall;
						using2 = true;
						//wall 3
						for(int v3 = v2+1; v3<n*m; v3++){
							int i3= (int )v3/m;
							int j3 = v3%m;
//							System.out.println("i3 , j3 : "+i3+ " , "+j3);

							boolean using3 = false;
							if(tmpMap[i3][j3]==blank){
								tmpMap[i3][j3]=wall;
								using3 = true;
								bfs();
								int tmp = cal();
								if(tmp>ans)
									ans=tmp;
							}
							if(using3){
								tmpMap[i3][j3] = blank;
							}
						}
						
					}
					
					if(using2){
						tmpMap[i2][j2]=blank;
					}
				}
			}
			
			
			if(using1){
				tmpMap[i][j] = blank;
			}
		}
		System.out.println(ans);

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) { // 첫 벽의 위치
//				boolean using1 = false;
//				if (map[i][j] == blank) { // 빈 곳의 위치에 벽을 놓는다.
//					map[i][j] = wall;
//					using1 = true;
//				}
//				for (int i2 = i; i2 < n; i2++) { // 두 번째 벽의 위치
//					for (int j2 = j; j2 < m; j2++) {
//						if (i2 == i && j2 == j)	// 첫 벽위치와 두번째 벽 위치가 겹치면 피한다.
//							continue; 
//						boolean using2 = false;
//						if (map[i2][j2] == blank) {
//							map[i2][j2] = wall;
//							using2 = true;
//						}
//
//						for (int i3 = i2; i3 < n; i3++) { // 세 번째 벽을 놓는다.
//							for (int j3 = j2; j3 < m; j3++) {
//								boolean using3 = false;
//								if (i3 == i2 && j3 == j2)	//세번째벽과 두번째 벽이 겹치는 위치면 패스 
//									continue;
//
//								if (map[i3][j3] == blank) {	//세번째 벽 세움 
//									map[i2][j2] = wall;
//									using3 = true;
//								}
//								if (using1 && using2 && using3) { // 벽을 세개 다 쓴
//																	// 경우
//									bfs();
//									int tmp = cal();
//									if(tmp>ans)
//										ans=tmp;
//									
//								} else {	//
//									continue;
//								}
//								if (using3) {	//마지막 장벽 원상복귀  
//									map[i3][j3] = blank;
//								}
//
//							}
//						}
//						if (using2) { // 원상태 복귀
//							map[i2][j2] = blank;
//						}
//
//					}
//				}
//
//				if (using1) { // 원상태 복귀
//					map[i][j] = blank;
//				}
//			}
//		}
//		System.out.println(ans);
	}

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static int cal() {
		int count =0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(copyMap[i][j]==blank)
					count++;
			}
		}
		return count;
	}

	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		copyMap = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copyMap[i][j] = tmpMap[i][j];
			}
		}

		boolean visited[][] = new boolean[n][m]; // bfs 방문 체크
		for (int i = 0; i < list.size(); i++) { // 바이러스 시작지점 다 집어넣어준다.
			q.add(list.get(i));
		}
		while (!q.isEmpty()) {
			int cx = q.peek().x;
			int cy = q.peek().y;
			q.poll();
			visited[cx][cy] = true;

			for (int k = 0; k < 4; k++) {
				int nx = cx + dx[k];
				int ny = cy + dy[k];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m)
					continue;
				if (!visited[nx][ny] && copyMap[nx][ny] == 0) {
					copyMap[nx][ny] = virus;
					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}
}
