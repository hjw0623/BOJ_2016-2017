package previous.done;
import java.util.Scanner;
/*
10
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0

-->16

10
2 2 2 2 2 2 2 32 2 2
2 0 0 0 2 0 0 0 0 2
2 0 0 0 2 0 0 0 0 2
2 0 0 2 2 2 2 2 2 2
2 0 0 0 0 0 0 0 0 2
2 0 2 2 2 0 0 0 0 2
2 0 0 0 0 0 16 0 0 2
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 0 0 0 0 0
2 0 0 0 0 2 2 2 2 2
-->32
4
2 0 2 8
2 0 4 2
2 2 4 8
2 2 4 4
--> 32
1
32
-->32
 */
public class BOJ_2048_completed {
	/**
	 * @param 2048
	 */
	static boolean merge[][];
	static int n;
	static int Max;
	static int count=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		Max = 0;
		int origin[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				origin[i][j] = sc.nextInt();
			}
		}	
		merge = new boolean[n][n];
		move(origin, 0, 0); //left
		move(origin, 0, 1); //right
		move(origin, 0, 2); //up
		move(origin, 0, 3); //down
		System.out.println(Max);

	}
    //merge 초기화
	public static void mergeInit() {
		for (int i = 0; i < merge.length; i++) {
			for (int j = 0; j < merge[i].length; j++) {
				merge[i][j] = false;
			}
		}
	}
	public static void copy(int [][]a, int [][]b){
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				a[i][j] = b[i][j];
			}
		}
	}
    //0 ~ n-1사이를 이동
	public static void move(int origin[][],  int step, int type) {
		int map[][] = new int [n][n];
		copy(map, origin);
		mergeInit();
		int stepMax=0;
		if (type == 0) {// move left
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < n; j++) {					
					if (map[i][j] == 0)
						continue;
					// 이동할 자리가 0이라면 계속 이동한다.
					while (map[i][j - 1] == 0 && j - 1 >= 0) {
						if(stepMax<map[i][j]){
							stepMax =map[i][j];
						}
                        //0인 곳 채워서 이동
						map[i][j - 1] = map[i][j];
						map[i][j] = 0;
                        
						//오버플로방지
						if(j-1!=0){
							j--;	
						}else {
							
						};
					}
					//최대한 이동한 j가 왼쪽값인 j-1과 같다면 j-1에서 합친다.
					if (map[i][j-1] == map[i][j] && merge[i][j-1] == false) {
						map[i][j-1] = map[i][j-1] * 2;
						if(stepMax<map[i][j-1]){
							stepMax = map[i][j-1];
						}
						map[i][j] = 0;
						merge[i][j-1] = true;
					}
				}
			}
		}
		else if(type==1){//moveright
			for (int i = 0; i < n; i++) {
				for (int j = n - 2; j >= 0; j--) {
					if (map[i][j] == 0)
						continue;
					// 이동할 자리가 0이라면 계속 이동한다.
					while (map[i][j + 1] == 0 && j + 1 <= n - 1) {
						if(stepMax < map[i][j]){
							stepMax = map[i][j];
						}
						map[i][j + 1] = map[i][j];
						map[i][j] = 0;
						if(j+1!=n-1){
							j++;	
						}else break;
					}
					//합치기
					if (map[i][j + 1] == map[i][j] && merge[i][j + 1] == false) {
						map[i][j + 1] = map[i][j + 1] * 2;
						map[i][j] = 0;
						merge[i][j + 1] = true;
						if (stepMax < map[i][j + 1]){
							stepMax = map[i][j + 1];
						}
					}
				}
			}
		}
		else if(type==2){//move Up
			for (int j = 0; j < n; j++) {
				for (int i = 1; i < n; i++) {
					if (map[i][j] == 0)
						continue;
					// 이동할 자리가 0이라면 계속 이동한다.
					while (map[i - 1][j] == 0 && i - 1 >= 0) {
						if(stepMax<map[i-1][j]){
							stepMax = map[i-1][j];
						}
						map[i - 1][j] = map[i][j];
						map[i][j] = 0;
						if(i-1 !=0){
							i--;
						}else break;
					}
					if (map[i - 1][j] == map[i][j] && merge[i - 1][j] == false) {
						map[i - 1][j] = map[i - 1][j] * 2;
						map[i][j] = 0;
						merge[i - 1][j] = true;
						if (stepMax < map[i - 1][j])
							stepMax = map[i - 1][j];
					}
				}
			}
		}
		else if(type==3){//move Down
			for (int j = 0; j < n; j++) {
				for (int i = n - 2; i >= 0; i--) {
					if (map[i][j] == 0)
						continue;
					// 이동할 자리가 0이라면 계속 이동한다.
					while (map[i + 1][j] == 0 && i + 1 <= n - 1) {
						if(stepMax<map[i+1][j]){
							stepMax = map[i+1][j];
						}
						map[i + 1][j] = map[i][j];
						map[i][j] = 0;
						if(i+1!=n-1){
							i++;	
						}else break;
					}
					// n-1 부터 j까지 탐색하며 두 수가 같다면 병합
					if (map[i + 1][j] == map[i][j] && merge[i + 1][j] == false) {
						map[i + 1][j] = map[i + 1][j] * 2;
						map[i][j] = 0;
						merge[i + 1][j] = true;
						if (stepMax < map[i + 1][j])
							stepMax = map[i + 1][j];
					}
				}
			}
		}
		if(stepMax>Max)Max = stepMax;
		if(step<4){
			move(map, step+1, 0);
//			print(map,step);
			move(map, step+1, 1);
//			print(map,step);
			move(map, step+1, 2);
//			print(map,step);
			move(map, step+1, 3);
		}else return;
	}
	public static void print(int[][]map, int step){
		System.out.println("step:" +step+ "count :"+count);
		
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
			System.out.print(map[i][j]+" ");
			}System.out.println();
		}
	}
}