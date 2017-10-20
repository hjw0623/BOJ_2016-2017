package heroes.samsung.test2;

import java.util.Scanner;

//https://www.acmicpc.net/problem/4920
//테트리스 게임
//4 
//70  2  1 7
// 7  1 30 6 
// 4 30 30 5 
// 3  1 30 2 
//0
public class Solution_4920 {
	static int type1X[] = { 0, 1, 2, 3 }; // 가로 ----
	static int type1Y[] = { 0, 0, 0, 0 };

	static int type2X[] = { 0, 0, 0, 0 };
	static int type2Y[] = { 0, 1, 2, 3 }; // 세로 |

	// ㄱㄴ
	static int type3X[] = { 0, 0, 1, 1 };
	static int type3Y[] = { 0, 1, 1, 2, };
	// type3 90회전
	// _|
	// |
	static int type4X[] = { 0, 1, 1, 2 };
	static int type4Y[] = { 0, 0, -1, -1 };
	// -ㄱ
	static int type5X[] = { 0, 0, 0, 1 };
	static int type5Y[] = { 0, 1, 2, 2 };
	// _|
	static int type6X[] = { 0, 1, 2, 2 };
	static int type6Y[] = { 0, 0, 0, -1 };
	// ㄴ-
	static int type7X[] = { 0, 1, 1, 1 };
	static int type7Y[] = { 0, 0, 1, 2 };
	// |-
	// |
	static int type8X[] = { 0, 0, 1, 2 };
	static int type8Y[] = { 0, 1, 0, 0, };
	// ㅜ
	static int type9X[] = { 0, 0, 0, 1 };
	static int type9Y[] = { 0, 1, 2, 1 };
	// ㅓ
	static int type10X[] = { 0, 1, 2, 1 };
	static int type10Y[] = { 0, 0, 0, -1 };
	// ㅗ
	static int type11X[] = { 0, 0, 0, -1 };
	static int type11Y[] = { 0, 1, 2, 1 };
	// ㅏ
	static int type12X[] = { 0, 1, 2, 1 };
	static int type12Y[] = { 0, 0, 0, 1 };
	// ㅁ
	static int type13X[] = { 0, 0, 1, 1 };
	static int type13Y[] = { 0, 1, 0, 1 };

	static int map[][];
	static int typeX[][] = { type1X, type2X, type3X, type4X, type5X, type6X, type7X,
							type8X, type9X, type10X, type11X,type12X, type13X };
	static int typeY[][] = { type1Y, type2Y, type3Y, type4Y, type5Y, type6Y, type7Y,
							type8Y, type9Y, type10Y, type11Y,type12Y, type13Y };
	static int answer[] ;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		answer = new int [125];
//		for(int i=0; i<answer.length;i++){
//			String s = sc.nextLine();
//			String arr[] = s.split(". ");
//			answer[i] = Integer.parseInt(arr[1]);
////			System.out.println(answer[i]);
//		}
//		
		int k = 1;
//		int mi=0, mj=0;
//		int mt = 0;
		while (true) {
			
			int ans = -4_000_000;
			int size = sc.nextInt();
			if (size == 0)
				break;

			map = new int[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int tmpMax = 0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					// 13개의 유형 모두 탐색
					for (int x = 0; x < 13; x++) {
						tmpMax = 0;
						for (int y = 0; y < 4; y++) {
							int nx = i + typeX[x][y];
							int ny = j + typeY[x][y];
							if (nx < 0 || ny < 0 || nx >= size || ny >= size) { 
								tmpMax = -4_000_000;
								break;
							}
							tmpMax += map[nx][ny];
						}
//						System.out.println("k : "+k);
						if (ans < tmpMax){
							ans = tmpMax;
//							mi=i; mj=j;
//							mt = x;
						}
					}
				}
			}
			System.out.println(k + ". " + ans);
//			if(ans!=answer[k-1]){
//				System.out.println(k+" 의 테케에러, 정답 : "+answer[k-1]+ " 오답 :"+ans);
//			}
			k++;
		}
	}
}
