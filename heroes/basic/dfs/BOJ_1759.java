package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//암호 만들기
//https://www.acmicpc.net/problem/1759

public class BOJ_1759 {
	static char moum[] = { 'a', 'e', 'i', 'o', 'u' };
	
	static int L, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		String[] alpha = new String[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			char tmp = st.nextToken().charAt(0);
			System.out.println(tmp);

			alpha[i] = ""+tmp;
		}
		
		Arrays.sort(alpha);
		
		dfs(L, alpha, "", 0);
	}

	public static void dfs(int len, String alpha[], String pw, int index) {
		
		if(len ==pw.length()){
			if(check(pw)){
				System.out.println(pw);
			}
			return;
		}
		if(index >=alpha.length)
			return;
		
		dfs(len,alpha,pw+alpha[index], index+1);
		dfs(len, alpha, pw, index+1);
	}
	
	public static boolean check(String str){
		int ja = 0;
		int mo = 0;
		for (char x : str.toCharArray()) {
			for(int i=0; i<moum.length; i++){
				if(x == moum[i])
					mo+=1;
					
				else
					ja+=1;
			}
		}
		return ja >= 2 && mo >= 1;
	}
}
