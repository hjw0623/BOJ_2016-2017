package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//째로 탈출 2
//https://www.acmicpc.net/problem/13460
/* 
째로탈출의 보드는 세로 N, 가로 M의 크기로 이뤄진 격자 형식으로 되어있다. 
가장 바깥의 행과 열은 모두 막혀 있다. 
그림에는 없지만 째로탈출에는 빨간 구슬과 파란 구슬이 하나씩 들어있고, 구멍이 하나 있다. 
게임의 목적은 빨간 구슬을 구멍을 통해 빼내는 것이다. 이 때, 파란 구슬이 구멍에 들어가면 안된다.

물론 째로탈출 보드에는 투명한 아크릴판이 덮여있어서 직접 빼내는 것은 불가능하고, 
중력을 이용해 이리저리 굴리면서 빼내게 된다. 플레이어가 할 수 있는 동작은 다음과 같이 네 종류가 있다.
왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기.

각각의 동작에서 공은 동시에 움직이게 되며, 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 
빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다. 
빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 
또, 빨간 구슬과 파란 구슬의 크기는 격자의 한 칸을 모두 차지한다. 
기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.

보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성

5 5
#####
#..B#
#.#.#
#RO.#
#####
1

7 7
#######
#...RB#
#.#####
#.....#
#####.#
#O....#
#######
5

7 7
#######
#..R#B#
#.#####
#.....#
#####.#
#O....#
#######
5

10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.#.#..#
#...#.O#.#
##########
-1

3 7
#######
#R.O.B#
#######
1

10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.##...#
#O..#....#
##########
7

 */

/**
 * @author hw
 * 
 *         비트마스크 dp를 이용해서 문제를 풀 수 있다. 1번~10번의 기회동안 각각 0~3의 상태가 존재한다. 각각의 경우에서 빨간
 *         공만 빠지는 경우가 존재하는지 체크하고, 파란 공이 빠지는 경우 -1
 * 
 *         dfs로도 문제를 풀 수 있다. 각각의 현재 상태에서 count++하는 식으로 재귀 호출하고, 현재 상태에서 상/하/좌/우에
 *         대한 재귀호출을 한다.
 * 
 *         공의 움직임에 주의한다. 현재 공의 위치를 먼저 체크하고, RB인 경우는 왼쪽으로 갈때는 R먼저, 오른쪽으로 갈 때는 B먼저
 *         R B 인 경우는 위로 갈 때 R먼저, 아래로 갈 때는 B먼저 이렇게 설정할 수도 있다.
 * 
 *         두 공의 현재 위치에 상관없이 끝까지 움직이고, 최초의 위치에 따라 위치가 겹치는 경우에는 움직인 방향에서 -1 만큼
 *         재조정도 가능.
 * 
 * 
 */

public class BOJ_13460 {
	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int UP = 2;
	static final int DOWN = 3;

	static final int WALL = -1;
	static final int RED = 1;
	static final int BLUE = 2;
	static final int PATH = 3;
	static final int HOLE = 4;

	static final int MAX = 11;
	static int origin[][];
	static int map[][];
	static int dp[];

	static void swap(int ax, int ay, int bx, int by) {
		int tmp = map[ax][ay];
		map[ax][ay] = map[bx][by];
		map[bx][by] = tmp;
	}

	static void initMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = origin[i][j];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String tmp = "";
		map = new int[N][M];
		origin = new int[N][M];
		dp = new int[1 << 20];
		int ry = 0, rx = 0;
		int by = 0, bx = 0;
		int originRx = 0; int originRy = 0;
		int originBx = 0; int originBy = 0;
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				if (tmp.charAt(j) == '#') {
					origin[i][j] = WALL;
				} else if (tmp.charAt(j) == '.') {
					origin[i][j] = PATH;
				} else if (tmp.charAt(j) == 'O') {
					origin[i][j] = HOLE;
				} else if (tmp.charAt(j) == 'R') {
					origin[i][j] = RED;
					originRx = i;
					originRy = j;
				} else if (tmp.charAt(j) == 'B') {
					origin[i][j] = BLUE;
					originBx = i;
					originBy = j;
				}
			}
		}

//		initMap();
//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map[i].length; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		Arrays.fill(dp, 11);
		// 상태는 0 ~ (1<<20)-1 인 경우가 존재한다.
		int i = 0;
		for (i = 0; i < (1 << 20); i++) {
//			System.out.println(Integer.toBinaryString(i) + " state ");
			int leftStep = i;
			boolean isFail = false; // 매 조합에서 실패한 경우를 체크 .
			boolean isSuccess = false;
			rx = originRx;
			ry = originRy;
			bx = originBx;
			by = originBy;
			// init map
			initMap();
			// 각 j단계별로 현재의 조합의 이동방향을 찾는다.
			for (int j = 1; j <= 10; j++) {
				int current = leftStep % 4;
				leftStep = leftStep >> 2;
//				if (current == LEFT) {
//					System.out.println("current move : LEFT "+rx+","+ry+" / "+bx+","+by);
//				} else if (current == RIGHT) {
//					System.out.println("current move : RIGHT "+rx+","+ry+" / "+bx+","+by);
//				} else if (current == UP) {
//					System.out.println("current move : UP "+rx+","+ry+" / "+bx+","+by);
//				} else if (current == DOWN) {
//					System.out.println("current move : DOWN "+rx+","+ry+" / "+bx+","+by);
//				}

				if (current == LEFT) { // B
					if (by <= ry) { // blue first up //R
						// 1. blue first
						for (int k = by; k >= 0; k--) {
							if (map[bx][k] == PATH) {
								swap(bx, k, bx, by);
								by = k;
								continue;
							} else if (map[bx][k] == WALL) {
								break;
							} else if (map[bx][k] == HOLE) {
								map[bx][by] = PATH;
								isFail = true;
								dp[i] = MAX;
								break;
							}
						}
						if (!isFail) { // red 이동
							for (int k = ry; k >= 0; k--) {
								if (map[rx][k] == PATH) {
									swap(rx, k, rx, ry);
									ry = k;
									continue;
								} else if (map[rx][k] == WALL || map[rx][k] == BLUE) {
									break;
								} else if (map[rx][k] == HOLE) {
									map[rx][ry] = PATH;
									isSuccess = true;
									dp[i] = j;
									break;
								}
							}
						}
//						print();
						if (isFail) { // 탈출에 실패하면 for문탈출
//							System.out.println("fail");
							break;
						}
						if (isSuccess && !isFail) {// 탈출에 성공하면 for문탈출
//							System.out.println("bingo");
							break;
						}
						if(isSuccess)
							break;

						// 2. red first move left 
					} else { // red first up
						// 1 red first
						for (int k = ry ; k >= 0; k--) {
							if (map[rx][k] == PATH) {
								swap(rx, k, rx, ry);
								ry =k;
								continue;
							} else if (map[rx][k] == WALL || map[rx][k] == BLUE) {
								break;
							} else if (map[rx][k] == HOLE) {
								map[rx][ry] = PATH;
								isSuccess = true;
								dp[i] = j;
								break;
							}
						}
						// 2 blue later
						for (int k = by ; k >= 0; k--) {
							if (map[bx][k] == PATH) {
//								System.out.println("swap "+ bx+", "+k+": " +map[bx][k]+ ", "+bx+", "+by+" :"+map[bx][by]);
								swap(bx, k, bx, by);
								by =k;
								continue;
							} else if (map[bx][k] == WALL) {
								break;
							} else if (map[bx][k] == HOLE) {
								map[bx][by] = PATH;
								isFail = true;
								dp[i] = MAX;
								break;
							}
						}
//						print();
						if (isFail) { // 탈출에 실패하면 for문탈출
							break;
						}
						if (isSuccess && !isFail) {// 탈출에 성공하면 for문탈출
//							System.out.println("bingo");
							break;
						}
					}
				} else if (current == RIGHT) {
					if (by >= ry) { // RB : blue first
						for (int k = by + 1; k < M; k++) {
							if (map[bx][k] == PATH) {
								swap(bx, k , bx, by);
								by  = k;
								continue;
							} else if (map[bx][k] == WALL) {
								break;
							} else if (map[bx][k] == HOLE) {
								isFail = true;
								map[bx][by] = PATH;
								break;
							}
						}
						if (!isFail) {
							for (int k = ry + 1; k < M; k++) {
								if (map[rx][k] == PATH) {
									swap(rx, k, rx, ry);
									ry = k;
									continue;
								} else if (map[rx][k] == WALL || map[rx][k] == BLUE) {
									break;
								} else if (map[rx][k] == HOLE) {
									map[rx][ry] = PATH;
									isSuccess = true;
									dp[i] = j;
									break;
								}
							}
						}
//						print();
						if (isFail) { // 탈출에 실패하면 for문탈출
//							System.out.println("fails");
							break;
						}
						if (isSuccess && !isFail) {// 탈출에 성공하면 for문탈출
//							System.out.println("bingo");
							break;
						}
					} else { // BR : red first
						for (int k = ry+1 ; k < M; k++) {
							if (map[rx][k] == PATH) {
								swap(rx, k, rx, ry);
								ry =k;
								continue;
							} else if (map[rx][k] == WALL) {
								break;
							} else if (map[rx][k] == HOLE) {
								map[rx][ry] = PATH;
								isSuccess = true;
								dp[i] = j;
								break;
							}
						}
						for (int k = by + 1; k < M; k++) {
							if (map[bx][k] == PATH) {
								swap(bx, k, bx, by);
								by = k;
								continue;
							} else if (map[bx][k] == WALL || map[bx][k] == RED) {
								break;
							} else if (map[bx][k] == HOLE) {
								isFail = true;
								map[bx][by] = PATH;
								break;
							}
						}
//						print();
						if (isFail) { // 탈출에 실패하면 for문탈출
							break;
						}
						if (isSuccess && !isFail) {// 탈출에 성공하면 for문탈출
//							System.out.println("bingo");
							break;
						}
					}
				} else if (current == UP) { 	// B
					if (bx <= rx) { 			// R: blue가 더 위 쪽에 있다면 blue를 먼저 이동
						for (int k = bx - 1; k >= 0; k--) { // bx에서 -1한 값부터 x좌표가
															// 최소 1까지 이동 가능.
							if (map[k][by] == PATH) { // 경로 이면 다음 턴으로 넘어간다.
								swap(k, by, bx, by);
								bx= k;
								continue;
							} else if (map[k][by] == WALL) { 
								break;
							} else if (map[k][by] == HOLE) { // 파란공=구멍 => 실패
								map[bx][by] = PATH; // 구멍을 만나면 이전 좌표를 비워준다.
								dp[i] = MAX; // 해당 조합은 실패다. 10보다 큰 임의의 수 넣는다.
								isFail = true;
								break;
							}
						}
						if (!isFail) {// blue가 들어가서 실패한 상황이 아니면 red를 이동시킨다.
							for (int k = rx - 1; k >= 0; k--) {
								if (map[k][ry] == PATH) {
									swap(k , ry, rx, ry);
									rx = k;
									continue;
								} else if (map[k][ry] == WALL || map[k][ry] == BLUE) {
								
									break;
								} else if (map[k][ry] == HOLE) {
									map[rx][ry] = PATH;
									dp[i] = j; // 현재 스텝에서 나온 경로를 저장.
									isSuccess = true;
									break;
								}
							}
						}
//						print();
						if (isFail) { // 탈출에 실패하면 for문탈출
							break;
						}
						if (isSuccess && !isFail) {// 탈출에 성공하면 for문탈출
//							System.out.println("bingo");
							break;
						}
					} else {	// R
								// B : red를 먼저 이동
								// 1.red first
						for (int k = rx - 1; k >= 0; k--) {
							if (map[k][ry] == PATH) {
								swap(k , ry, rx, ry);
								rx = k;
								continue;
							} else if (map[k][ry] == WALL) {
								
								break;
							} else if (map[k][ry] == HOLE) {
								map[rx][ry] = PATH;
								dp[i] = j; // 현재 스텝에서 나온 경로를 저장.
								isSuccess = true;
								break;
							}
						}
						// 2.blue
						for (int k = bx - 1; k >= 0; k--) { // bx에서 -1한 값부터 x좌표가
															// 최소 1까지 이동 가능.
							if (map[k][by] == PATH) { // 경로 이면 다음 턴으로 넘어간다.
								swap(k, by, bx, by);
								bx = k;
								continue;
							} else if (map[k][by] == WALL || map[k][by] == RED) { // 벽
								break;
							} else if (map[k][by] == HOLE) { // 파란공=구멍 => 실패
								map[bx][by] = PATH; // 구멍을 만나면 이전 좌표를 비워준다.
								dp[i] = MAX; // 해당 조합은 실패다. 10보다 큰 임의의 수 넣는다.
								isFail = true;
								break;
							}
						}
//						print();
						if (isFail) { // 탈출에 실패하면 for문탈출
							break;
						}
						if (isSuccess && !isFail) {// 탈출에 성공하면 for문탈출
//							System.out.println("bingo");
							break;
						}
					}
				} else if (current == DOWN) {
					if (bx >= rx) { // R
									// B : blue를 먼저 이동
						// 1. blue 이동
						for (int k = bx + 1; k < N; k++) {
							if (map[k][by] == PATH){
								swap(k , by, bx, by);
								bx = k;
								continue;
							}
							else if (map[k][by] == WALL) {
								break;
							} else if (map[k][by] == HOLE) {
								map[bx][by] = PATH;
								dp[i] = MAX;
								isFail = true;
								break;
							}
						}
						// 2. red later
						if (!isFail) {
							for (int k = rx + 1; k < N; k++) {
								if (map[k][ry] == PATH){
									swap(k , ry, rx, ry);
									rx = k;
									continue;
								}
								else if (map[k][ry] == WALL || map[k][ry] == BLUE) {
									
									break;
								} else if (map[k][ry] == HOLE) {
									isSuccess = true;
									map[rx][ry] = PATH;
									dp[i] = j;
									break;
								}
							}
						}
//						print();
						if (isFail) { // 탈출에 실패하면 for문탈출
							break;
						}
						if (isSuccess && !isFail) {// 탈출에 성공하면 for문탈출
//							System.out.println("bingo");
							break;
						}
					} else {
						// 1. B
						// R : red first
						for (int k = rx + 1; k < N; k++) {
							if (map[k][ry] == PATH){
								swap(k , ry, rx, ry);
								rx = k;
								continue;
							}
							else if (map[k][ry] == WALL) {
								break;
							} else if (map[k][ry] == HOLE) {
								isSuccess = true;
								map[rx][ry] = PATH;
								dp[i] = j;
								break;
							}
						}
						// 2. blue later
						for (int k = bx + 1; k < N; k++) {
							if (map[k][by] == PATH){
								swap(k , by, bx, by);
								bx =k;
								continue;
							}
							else if (map[k][by] == WALL || map[k][by] == RED) {
								break;
							} else if (map[k][by] == HOLE) {
								isFail = true;
								map[bx][by] = PATH;
								dp[i] = MAX;
								break;
							}
						}
//						print();
						if (isFail) { // 탈출에 실패하면 for문탈출
							break;
						}
						if (isSuccess && !isFail) {// 탈출에 성공하면 for문탈출
//							System.out.println("bingo");
							break;
						}
					}
				}
			}
		}

		Arrays.sort(dp);
		if (dp[0] < 11)
			System.out.println(dp[0]);
		else
			System.out.println(-1);

	}

	static void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
