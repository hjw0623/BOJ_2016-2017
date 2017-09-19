package basic.binaryOximalHeximal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1373 {
	// 11001100 (2) = 314 (8)
	// 011 001 100 (2) =
	// 3 2 1
	// 987 654 321

	// 3 1 4
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		// 전처
		if (input.length() % 3 == 1) {
			input = "00" + input;
		} else if ((input.length() % 3 == 2)) {
			input = "0" + input;
		}
		int len = input.length();
		int retlen = (len / 3);
		int result[] = new int[retlen];
		int k = 0;
		for (int i = len - 1; i >= 0; i -= 3) {
			String tmp = "" + input.substring(i - 2, i + 1);
			int decimal = 0;

			int third = (tmp.charAt(2) - '0') * 1;
			int second = ( tmp.charAt(1) - '0' )* 2;
			int first = (tmp.charAt(0) - '0' )* 4;
			decimal = third + second + first;
//			System.out.println("decimal "+decimal);
			result[retlen-k-1] = decimal;
			k++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<retlen; i++){
			sb.append(result[i]);
		}
		System.out.println(sb.toString());
	}

}
