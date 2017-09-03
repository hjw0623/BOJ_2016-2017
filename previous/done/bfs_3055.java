package previous.done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	public int x;
	public int y;
	public int attr;

	public Point() {
	}

	public Point(int x, int y, int attr) {
		this.x = x;
		this.y = y;
		this.attr = attr;
	}
}

public class bfs_3055 {
	static Point map[][];
	static int dx[] = { 0, 0, -1, 1 }; // down up left right
	static int dy[] = { 1, -1, 0, 0 };
	static int r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new Point[r][c];
		int startx = 0;
		int starty = 0;
		
		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				char a = line.charAt(j);
				if (a == 'S') { // start
					map[i][j] = new Point(i, j, 0);
					startx = i;
					starty = j;
				}
				if (a == '.') { // path
					map[i][j] = new Point(i, j, 1);
				}
				if (a == 'D') { // destination
					map[i][j] = new Point(i, j, 2);
				}
				if (a == 'X') { // rock
					map[i][j] = new Point(i, j, -1); // 음수인 영역을 만나면 나아갈 수 없다.
				}
				if (a == '*') { // water
					map[i][j] = new Point(i, j, 3);
//					water.add(map[i][j]); // 미리 수원지를 큐에 담는다.
				}
			}
		}
		bfs(startx, starty);
	}
	public static void printMap(){
		for(int i=0; i<r; i++){
			for(int j=0; j<c; j++){
				System.out.print(map[i][j].attr+" ");
			}System.out.println();
		}
	}
	public static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>(); // 고슴도치의 이동경로 큐
		Queue<Point> water  = new LinkedList<>();
		for(int ii=0; ii<r; ii++){
			for(int jj=0; jj<c; jj++){
				if(map[ii][jj].attr==3){
					water.offer(map[ii][jj]);
				}
			}
		}
		queue.add(map[i][j]);
		int tmp = -1; //이동 거리
		Point cr = queue.peek();
		System.out.println("최초 고슴도치 :" +cr.x+" "+cr.y);

		
		
		while (!queue.isEmpty()) {
			for (Point point : queue) {
				if(point.attr !=0){
					queue.remove(point);
				}
			}
			Point cur = queue.poll();
			map[cur.x][cur.y].attr=0;
			Point wa = water.peek();
			System.out.println("최초 물 : "+wa.x+ " "+wa.y+" "+wa.attr);
			//물을 먼저 이동시킨다.
			System.out.println("물큐 크기: "+water.size());
			
			int a = water.size();
			for(int k=a; k>0; k--){
				Point ww = water.poll();//현재의 물 좌표 (waterSize만큼 출현)
				a--;
				System.out.println("water 현재 위치 : "+ww.x +" "+ww.y);
						
				for(int ll=0; ll<4; ll++){
					int nextWaterX = ww.x+dx[ll];
					int nextWaterY = ww.y+dy[ll];
					//범위내 존재
					if(nextWaterX >=0 && nextWaterY >=0 && nextWaterX<r && nextWaterY<c){
						//물이 갈 수 있는 곳이면
						if((map[nextWaterX][nextWaterY].attr==1 )|| (map[nextWaterX][nextWaterY].attr==0)){
							System.out.println("물 삽입");
							water.add(map[nextWaterX][nextWaterY]);
							map[nextWaterX][nextWaterY].attr=3;
						}
						//물이 갈 수 없는 곳이라면 패스한다.
						if((map[nextWaterX][nextWaterY].attr==-1) || 	//바위거나
							(map[nextWaterX][nextWaterY].attr==3) ||			//이미 물로 차있거나
							(map[nextWaterX][nextWaterY].attr==2) ){ 			//비버 집이라면
							continue;
						}
					}
				}
			}
			printMap();
				
			
			//고슴도치의 이동 시작
			for (int k = 0; k < 4; k++) {
				int nx = cur.x + dx[k];
				int ny = cur.y + dy[k];
				// 범위안에 존재시
				if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
					if (map[nx][ny].attr == 1) { // path면
						System.out.println("다음 이동 : "+nx+" "+ny);
						queue.add(map[nx][ny]);
						map[nx][ny].attr=0;
						map[cur.x][cur.y].attr=1;
					}
					//성공시
					else if(map[nx][ny].attr ==2){ //destination이면 
						System.out.println("경로 사이즈 : "+tmp);
						return;
					}
				}
			}
			tmp++;//횟수 증가
		}
		
		System.out.println("KAKTUS"); //실패시
	}

}
