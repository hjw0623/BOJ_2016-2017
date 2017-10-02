package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//종이조각 https://www.acmicpc.net/problem/14391
// 모든 i, j는 가로요소/세로요소 둘 중 하나의 상태를 갖는다. --> 비트마스크 적용 가능.
/* 이를테면 다음의 상태에 대해서 
2 3
123
312

111
111 --> 가로로 123+312 = 435가 된다.

000
000 --> 세로로 12+21+32  = 65가 된다.

110
110 --> 가로로 12+31+세로로 32 = 75가 된다. 

연속된 1 || 0 의 자릿수를 보정하는 작업이 필요. 

예제의 
4937
2591
3846
9150 에서 

1110
0010   == 1110 0010 0000 1100인 이진수 
0000
1100 인 경우이므로  1의 경우 가로로 연속된 1의 개수를 카운트하고, 0의 경우, 세로로 연속된 0의 개수를 카운트하여 해당요소*10*cnt하면 된다. 

*/
public class BOJ_14391 {
	static int arr[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = tmp.charAt(j) - '0';
			}
		}
		int ret = 0;
		// 상태는 0~ N*M-1까지 존재
		for (int state = 0; state < (1 << (N * M)); state++) {
			int max = 0;
			// 가로 합을 계산
			for (int row = 0; row < N; row++) {
				int rowSum = 0;
				int cnt = 0;
				for (int col = 0; col < M; col++) {
					// 맨 윗줄 (0,0) 부터 (0,1) (0,2) 순으로 비교하기 위해 2차원 배열의 1차원 배열 환산주소 계산 
					int idx = 1 <<  (((N - row) * M - col)-1);
//					System.out.println(
//							"state : " + Integer.toBinaryString(state) +" row, col : "+row+", "+col+"// comp : " + Integer.toBinaryString(idx));
					if ((state & (idx)) != 0) {// 가로라면
						rowSum = rowSum * 10 + arr[row][col];
					} else { // 세로 조합 요소면 패스
						max += rowSum;
						rowSum = 0;
					}
				}
				max += rowSum;
			}
			// 세로 합을 계산
			for (int col = 0; col < M; col++) {
				int colSum = 0;
				for (int row = 0; row < N; row++) {
					// 0,0부터 1,0, 2,0 ...순으로 비교 
					int idx = 1 << (((N - row) * M - col)-1);
//					System.out.println(
//							" 세로 state : " + Integer.toBinaryString(state) + " comp : " + Integer.toBinaryString(idx));
					if((state & (idx)) ==0){//세로라면 
						colSum = colSum*10 + arr[row][col];
					}else{
						max +=colSum;
						colSum =0;
					}
				}
				max +=colSum;
			}
//			System.out.println("가로 + 세로 max :"+max);
			if(ret<max){
				ret = max;
			}
		}
		System.out.println(ret);
	}
}
