package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {
	static int map[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		map = new int[N];
		int bitmask = (1 << N);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		int cnt = 0;
		// 가능한 모든 조합의 경우 1 ~ 11111 (31)
		for (int i = 1; i < bitmask; i++) {
			int ret = 0;
			for(int j=0; j<N; j++){
				if(( i & 1<<j )!=0){
					ret +=map[j];
				}
			}
			if(ret==S)
				cnt++;
		}
		System.out.println(cnt);
	}
}
