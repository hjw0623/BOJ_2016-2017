package heroes.samsung.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
//1
//7    
//0 0 1 0 0 0 0
//0 0 1 0 0 0 0
//0 0 0 0 0 1 0
//0 0 0 0 0 0 0
//1 1 0 1 0 0 0
//0 1 0 0 0 0 0
//0 0 0 0 0 0 0
import java.util.StringTokenizer;

//12
public class SW_1767 {
	static int map[][];
//	static int tmpMap[][];
	static boolean visited[][];
	static int T, N;
	static final int CORE = 1;
	static final int BLANK = 0;
	static int LIMIT =0;
	static int ret = 0; 
	static int coreCnt = 0; 
	
	static int connectedCore = 0; //전선으로 연결한 코어 
	static int line =Integer.MAX_VALUE;; //전선의 개수 
	
	//방향 
	static final int LEFT = 0;
	static final int UP = 1;
	static final int DOWN = 2;
	static final int RIGHT = 3;
	static final int NOTHING = 4;
	
	static class Point  {
		int x;
		int y;
		int d;
		public Point() {
		}

		public Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public void setDistance(int d){
			this.d = d;
		}
	}
//	static class Comp implements Comparator<Point>{
//		@Override
//		public int compare(Point o1, Point o2) {
//			if(o1.x <o2.x){
//				return -1;
//			}else if(o1.x==o2.x){
//				if(o1.y<o2.y)return -1;
//				else return 1;
//			}
//			return 1;
//		}
//	}
//	public static void init(){
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				tmpMap[i][j] = map[i][j];
//			}
//		}
//	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			ret = 0;
			visited= new boolean[N][N];
			ArrayList<Point> qq = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
//					tmpMap[i][j] = map[i][j];
					if (map[i][j] == 1 && i != 0 && j != 0) {
						qq.add(new Point(i, j, 0));
					} else if (map[i][j] == 1 && (i == 0 || j == 0)) {
					}
				}
			}
//			Collections.sort(qq, new Comp());
			
			LIMIT = qq.size();
			long max = (long)(Math.pow(4, LIMIT));
			for(int i=0; i<  max; i++){
				int arr[] = gen(i);
//				init();	//임시배열 초기화
				visited = new boolean[N][N];
				
				int tmpLine =0;	//매 경우의 수에 연결된 전선의 개수 
				int tmpConnected = 0;
//				System.out.println("----");
				for(int k=0; k<qq.size(); k++){
					Point cur = qq.get(k);
					int cx = cur.x;
					int cy = cur.y;
					
					//특정 코어의 왼쪽으로 전선 놓을 때 
					if(arr[k] == LEFT){
						boolean isOk = true;
						for(int j=cy-1; j>=0; j--){
							tmpLine++; //전선 개수 증가 
							//충돌하면 이전 
							if(map[cx][j]==1 || visited[cx][j]){ 
								isOk=false;
								break;
							}
							visited[cx][j]=true; //전선 놓음 
						}
						if(isOk){
							tmpConnected++;
						}else{
							tmpConnected =0; 
							continue;
						}
					}
					//오른쪽으로 전선 
					else if(arr[k]==RIGHT){
						boolean isOk = true;
						for(int j=cy+1; j<N; j++){
							tmpLine++; //전선 개수 증가 
							if(map[cx][j]==1 || visited[cx][j]){ //충돌하면 그냥 탈출 
								isOk=false;
								break;
							}
							visited[cx][j]=true; //전선 놓음 
							
						}
						if(isOk){
							tmpConnected++;
						}else{
							tmpConnected =0; 
							continue;
						}
					}
					else if(arr[k]==UP){
						boolean isOk = true;
						for(int j=cx-1; j>=0; j--){
							tmpLine++; //전선 개수 증가 
							if(map[j][cy]==1 || visited[j][cy]){ //충돌하면 그냥 탈출 
								isOk=false;
								break;
							}
							visited[j][cy]=true; //전선 놓음 
							
						}
						if(isOk){	//충돌 안했다면 연결 코어 증가 
							tmpConnected++;
						}else{	//충돌하는 경우가 있다면 그냥 노카운트 
							tmpConnected =0; 
							continue;
						}
					}
					else if(arr[k]==DOWN){
						boolean isOk = true;
						for(int j=cx+1; j<N; j++){
							tmpLine++; //전선 개수 증가 
							if(map[j][cy]==1 || visited[j][cy]){ //충돌하면 그냥 탈출 
								isOk=false;
								break;
							}
							visited[j][cy]=true; //전선 놓음 
							
						}
						if(isOk){	//충돌 안했다면 연결 코어 증가 
							tmpConnected++;
						}else{	//충돌하는 경우가 있다면 그냥 노카운트 
							tmpConnected =0; 
							continue;
						}
					}
				}
				//매 경우마다 갱신 여부 체크 
				if(tmpConnected>0){
					if(tmpConnected>connectedCore){	//연결된 코어가 더 많다면 갱신 
						connectedCore = tmpConnected;
						line  = tmpLine;
					}else if(tmpConnected== connectedCore){//코어가 같은데 전선수가 더 작으면 갱신 
						if(line > tmpLine){
							line = tmpLine;
						}
					}
				}
				tmpLine =0;
				tmpConnected=0;
			}
			System.out.println("#"+t+" "+line);
		}
	}
	static int [] gen(int k){
		int dir[] = new int [LIMIT];
		for(int i=0; i<LIMIT; i++){
			dir[i] = k&3; //k%3
			k<<=2;	//k/4
		}
		return dir;
	}

}
