package kakao.firstblind.solution6;

public class Solution {
	// 입력으로 블록의 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작
	public static int solution(int m, int n, String[] board) {
		// 2x2조건이 되는 모든 구간을 탐색
		int answer = 0;
		boolean isDeleted[][] = new boolean[m][n];
		int check4Box[][] = new int[m][n];
		char boardArray[][] = new char[m][n];
		for (int i = 0; i < m; i++) {
			boardArray[i] = board[i].toCharArray();
		}
		for (int i = 0; i < m; i++) { // 높이
			for (int j = 0; j < n; j++) { // 너비
				check4Box[i][j] = 1;
			}
		}
		boolean isNext = true;
		int count = 0;
		char empty = ' ';
		while (isNext) {
			isNext = false;
			// 갱신 가능성 탐색
			for (int i = 0; i < m - 1; i++) { // 높이
				for (int j = 0; j < n - 1; j++) { // 너비
					if (boardArray[i][j] == empty)
						continue;
					else if (boardArray[i][j] == boardArray[i][j + 1] && boardArray[i][j] == boardArray[i + 1][j]
							&& boardArray[i][j] == boardArray[i + 1][j + 1]) {
						isDeleted[i][j] = isDeleted[i][j + 1] = isDeleted[i + 1][j] = isDeleted[i + 1][j + 1] = true;
						isNext = true;
					}
				}
			}
			// System.out.println("===check deleete====");
			//
			// for (int i = 0; i < m; i++) {
			// for (int j = 0; j < n; j++) {
			// System.out.print(isDeleted[i][j] + " ");
			// }
			// System.out.println();
			// }

			// 지운다.
			//
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < m; i++) {
					if (isDeleted[i][j] == true) {
						count++;
						boardArray[i][j] = ' ';
						// isDeleted[i][j] = false;
					}
				}
			}
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < m - 1; i++) {
					int k = 0;

					while (k + 1 < m) {
						if (boardArray[k][j] != ' ' && boardArray[k + 1][j] == ' ') {
							char tmp = boardArray[k + 1][j];
							boardArray[k + 1][j] = boardArray[k][j];
							boardArray[k][j] = tmp;

						}
						k++;
					}
				}

			}
			// 초기화
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					isDeleted[i][j] = false;
				}
			}
			// print
			// System.out.println("===after====");
			// for (int i = 0; i < m; i++) {
			// for (int j = 0; j < n; j++) {
			// System.out.print(boardArray[i][j] + " ");
			// }
			// System.out.println();
			// }

		}

		return answer = count;
	}

	public static void main(String[] args) {

		int m1 = 4;
		int n1 = 5;
		String str1[] = { "CCBDE", "AAADE", "AAABF", "CCBBF" };
		System.out.println(solution(m1, n1, str1));
		int m2 = 6;
		int n2 = 6;
		String str2[] = { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" };

		System.out.println(solution(m2, n2, str2));

	}
}
