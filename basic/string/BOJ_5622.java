package basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_5622 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		HashMap<String, Integer> map = new HashMap<>();
		char start = 'A' - 1;

		for (int i = 2; i <= 9; i++) {
			if (i == 7 || i == 9) {
				for (int j = 0; j < 4; j++) {
					String cur = "";
					start = (char) (start + 1);
					cur = cur + start;
					map.put(cur, i+1);
				}
			} else {
				for (int j = 0; j < 3; j++) {
					String cur = "";
					start = (char) (start + 1);
					cur = cur + start;
					map.put(cur, i+1);
				}
			}
		}

		Integer totalTime = 0;
		for (int i = 0; i < line.length(); i++) {
			String k = "" + line.charAt(i);
			totalTime += map.get(k);
		}
		System.out.println(totalTime);
	}
}
