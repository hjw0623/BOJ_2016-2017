package Kakao_CodeFest.solution4;

public class Solution {
	static int MOD = 20170805;
	// m, n <=500. 배열 원소값은 0,1,2 중 하나.
	// cityMap[0][0] = 0, cityMap[m-1][n-1] = 0
	static final int fromLeft = '0';
	static final int fromUp = '1';

	static class NodePair {
		public Node LeftNode;
		public Node UpNode;

		public NodePair(Node left, Node up) {
			this.LeftNode = left;
			this.UpNode = up;
		}
	}

	static class Node {
		public int x;
		public int y;
		public int cumulated; // 누적 경로

		public Node(int x, int y, int c) {
			this.x = x;
			this.y = y;
			cumulated = c;
		}

	}

	public static int solution(int m, int n, int[][] cityMap) {
		int answer = 0;
		NodePair map[][] = new NodePair[m][n];
		// 초기화
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				Node left = new Node(i, j, 0);
				Node up = new Node(i, j, 0);
				map[i][j] = new NodePair(left, up);
			}
		}
		
		map[0][0] = new NodePair(new Node(0,0,1), new Node(0,0,1));
		// 0행 초기화 
		for (int j = 0; j < n; j++) {
			if(cityMap[0][j]==0){
				map[0][j].LeftNode.cumulated =1;
				map[0][j].UpNode.cumulated =1;
			}
			if (cityMap[0][j] != 1)
				map[0][j].LeftNode.cumulated = 1;
			else
				break;
		}
		// 0열 초기화  
		for (int i = 0; i < n; i++) {
			if (cityMap[i][0] != 1) {
				map[i][0].UpNode.cumulated = 1;
			} else
				break;
		}
		
		// 경우의 수
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if(cityMap[i][j]==2){
					map[i][j].LeftNode.cumulated += map[i][j - 1].LeftNode.cumulated%MOD;
					map[i][j].UpNode.cumulated += map[i - 1][j ].UpNode.cumulated%MOD;
					map[i][j].LeftNode.cumulated = map[i][j].LeftNode.cumulated %MOD;
					map[i][j].UpNode.cumulated  = map[i][j].UpNode.cumulated %MOD;
				}else if( cityMap[i][j]==0) {
					map[i][j].LeftNode.cumulated += (map[i][j-1].LeftNode.cumulated%MOD+map[i-1][j].UpNode.cumulated%MOD)%MOD;
					map[i][j].UpNode.cumulated += (map[i][j-1].LeftNode.cumulated%MOD+ map[i-1][j].UpNode.cumulated%MOD)%MOD;
					map[i][j].LeftNode.cumulated = map[i][j].LeftNode.cumulated %MOD;
					map[i][j].UpNode.cumulated  = map[i][j].UpNode.cumulated %MOD;
				}
			}
		}
//		for (int i = 0; i < m; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print("("+map[i][j].LeftNode.cumulated+", "+map[i][j].UpNode.cumulated+") ");
//			}
//			System.out.println();
//		}
		if(m!=1 || n!=1){
			answer = map[m - 1][n - 1].LeftNode.cumulated%MOD + map[m - 1][n - 1].UpNode.cumulated%MOD;
			answer = answer%MOD;
		}
		else if(m==1){
			answer =  map[m - 1][n - 1].UpNode.cumulated%MOD;
			
		}else{
			answer = map[m - 1][n - 1].LeftNode.cumulated%MOD;
		}
		return answer;
	}

	public static void main(String[] args) {
		int arr[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }; // 6
		int arr2[][] = { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } }; // 2

		System.out.println(solution(3, 3, arr));
		System.out.println(solution(3, 6, arr2));

	}

}
