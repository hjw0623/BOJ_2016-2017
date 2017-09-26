package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10987 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ss = br.readLine();
		char moum[] = { 'a', 'e', 'i', 'o', 'u' };
		int count =0;
		for (int i = 0; i < ss.length(); i++) {

			for (int j = 0; j < moum.length; j++) {
				if (ss.charAt(i) == moum[j]) {
					count++;
				}
			}
		}
		System.out.println(count);
	}

}
