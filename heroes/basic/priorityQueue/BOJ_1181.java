package heroes.basic.priorityQueue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1181 {
	public static class CompWord implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			if (o1.length() < o2.length())
				return -1;
			else if (o1.length() == o2.length()) {
				for (int i = 0; i < o1.length(); i++) {
					if (o1.charAt(i) < o2.charAt(i)) {
						return -1;
					} else if (o1.charAt(i) == o2.charAt(i) && i < o1.length() - 1) {
						continue;
					} else {
						return 1;
					}
				}
			}
			return 1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter print = new BufferedWriter(new OutputStreamWriter(System.out));
		PrintWriter out = new PrintWriter(print);
		int num = Integer.parseInt(br.readLine());

		String arr[] = new String[num];

		for (int i = 0; i < num; i++) {
			arr[i] = br.readLine();
		}
		CompWord comp = new CompWord();
		Arrays.sort(arr, comp);

		// 단, 같은 단어가 여러 번 입력된 경우에는 한 번씩만 출력한다.
		System.out.println(arr[0]);
		if (arr.length > 1) {
			for (int i = 1; i < arr.length; i++) {
				if (arr[i].equals(arr[i - 1])) {
					continue;
				}
				System.out.println(arr[i]);
			}
		}
	}
}
