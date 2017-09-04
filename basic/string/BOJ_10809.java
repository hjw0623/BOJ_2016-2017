package basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//알파벳 소문자로만 이루어진 단어 S가 주어진다. 
//각각의 알파벳에 대해서, 단어에 포함되어 있는 경우에는 처음 등장하는 위치를, 
//포함되어 있지 않은 경우에는 -1을 출력하는 프로그램
public class BOJ_10809 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		char tmp[] = br.readLine().toCharArray();
		int alpha[] = new int[26];
		Arrays.fill(alpha, -1);

		for (int i = 0; i < tmp.length; i++) {
			if (alpha[tmp[i] - 'a'] == -1)
				alpha[tmp[i] - 'a'] = i;
		}
		for (int i = 0; i < alpha.length; i++) {
			System.out.print(alpha[i] + " ");
		}
	}
}
