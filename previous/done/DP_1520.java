package previous.done;

import java.util.Scanner;

public class DP_1520 {
	static int n,m; //n은 가로, m은 세로
	static int dp[][];
	static int map[][];
	static int nx[] = {0,0,-1,1};
	static int ny[] = {-1,1,0,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		m = sc.nextInt(); //세로 4
		n = sc.nextInt(); //가로 5
		map = new int [m][n];
		dp = new int [m][n];
		
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				map[i][j] = sc.nextInt();
			}
		}
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
			}
		}
		System.out.println(go(0,0));
	}
	public static int go(int x, int y){
		if(x==m-1 && y== n-1)return 1;
		if(dp[x][y]>0)return dp[x][y];
		for(int i=0; i<4; i++ ){
			int next_x = x+nx[i];
			int next_y = y+ny[i];
			if(next_x < m && next_x >=0 && next_y < n &&next_y >= 0 ){
				if(map[x][y]>map[next_x][next_y]){
					dp[x][y] += go(next_x, next_y);
				}
			}
		}
		return dp[x][y];
	}

}
