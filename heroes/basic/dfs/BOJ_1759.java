package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//암호 만들기
//https://www.acmicpc.net/problem/1759

public class BOJ_1759 {
	static char moum[] = { 'a', 'e', 'i', 'o', 'u' };
	static ArrayList<Character> m1, j1;
	static ArrayList<String> dp;
	static int L, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		m1 = new ArrayList<Character>();
		j1 = new ArrayList<Character>();
		dp  = new ArrayList<String>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			char tmp = st.nextToken().charAt(0);
			for (int k = 0; k < moum.length; k++) {
				if (moum[k] == tmp)
					m1.add(tmp);
				else
					j1.add(tmp);
			}
		}
//		System.out.println(dp.size());
//		Collections.sort(dp);
//		for(int i=1; i<dp.size()-1; i++){
//			System.out.println(dp.get(0));
//			if(dp.get(i-1).equals(dp.get(i))){
//				continue;
//			}else{
//				System.out.println(dp.get(i));
//			}
//		}
	}

	public static void dfs(int len, String alpha[], String pw, int index) {
		

	}

	

}
