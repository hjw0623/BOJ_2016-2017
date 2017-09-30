package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1987
//알파벳
//판다문제와 유사. 이미 탐색한 지점도 재 탐색해야 한다. 
public class BOJ_1987 {
//	static HashMap<Character, Integer> hash ;
	static int ret = 0;
	static int R;
	static int C;
	static int dp[][];
	static char map[][];
	static boolean alpha[];
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		 R = Integer.parseInt(st.nextToken());
		 C = Integer.parseInt(st.nextToken());
		dp = new int[R][C];
		map = new char[R][C];
		alpha = new boolean[26];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			char tmp[] = str.toCharArray();
			
			for (int j = 0; j < tmp.length; j++) {
				map[i][j] = tmp[j];
			}
		}
		//시작점 입력 
		alpha[map[0][0]-'A'] = true;
		ret = dfs(0, 0);
		System.out.println(ret);
	}

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	
	public static int dfs(int x, int y) {
		// 메모
		int ans = 0;
		int tmp = 0;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
				continue;
			}
			if (!alpha[map[nx][ny]-'A']) {
				alpha[map[nx][ny]-'A'] = true;
				tmp = dfs(nx, ny) ;
				ans = Math.max(tmp, ans);
				alpha[map[nx][ny]-'A'] = false;
			}
		}
		return ans+1;
	}
}


/*
 
2 4
CAAB
ADCB

3 

3 6
HFDFFB
AJHGDH
DGAGEH

6

I E H F K L A G C M
5 5
IEFCJ
FHFKC
FFALF
HFGCF
HMCHH

10
*/