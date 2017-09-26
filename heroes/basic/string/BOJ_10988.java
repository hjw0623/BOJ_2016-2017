package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//팰린드롬인지 확인하기
//팰린드롬이란 앞으로 읽을 때와 거꾸로 읽을 때 똑같은 단어를 말한다. 
//level, noon은 팰린드롬이고, baekjoon, online, judge는 팰린드롬이 아니다.
public class BOJ_10988 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ss = br.readLine();
		char arr[] = ss.toCharArray();
		if (isPal(ss))
			System.out.println(1);
		else
			System.out.println(0);
	}

	public static boolean isPal(String str) {
		// basis 길이 1, 길이 2
		if (str.length() == 1)
			return true;
		else if (str.length() == 2 && str.charAt(0) == str.charAt(1))
			return true;
		else if (str.length() == 2 && str.charAt(0) != str.charAt(1))
			return false;
		
		// recursive 
		else {// 길이가 2보다 크면
			if (str.charAt(0) == str.charAt(str.length() - 1)) {
				String next = str.substring(1, str.length()-1);
				return isPal(next);
			} else {
				return false;
			}
		}

	}
}
