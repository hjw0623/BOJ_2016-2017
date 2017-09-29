package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11723
/*

비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.

add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
check x: S에 x가 있으면 1을, 없으면 0을 출력한다.
toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
all: S를 {1, 2, ..., 20} 으로 바꾼다.
empty: S를 공집합으로 바꾼다. 
 */
public class BOJ_11723 {
	public static void add(int n) {
		if (!set.contains(n))
			set.add(n);
	}

	public static void remove(int n) {
		if (set.contains(n))
			set.remove(n);
	}

	public static void check(int n) {

	}

	public static void toggle(int n) {
		if (set.contains(n)) {
			set.remove(n);
		} else {
			set.add(n);
		}
	}

	public static void all() {
		for (int i = 1; i <= 20; i++) {
			if (!set.contains(i))
				set.add(i);
		}
	}

	public static void empty() {
		set.clear();
	}

	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer num = Integer.parseInt(st.nextToken());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("add")) {
				int n = Integer.parseInt(st.nextToken());
				add(n);
				continue;
			} else if (command.equals("check")) {
				int n = Integer.parseInt(st.nextToken());
				if (set.contains(n)) {
					sb.append(1);
					sb.append("\n");
			

				} else {
					sb.append(0);
					sb.append("\n");
				}
				continue;
			} else if (command.equals("remove")) {
				int n = Integer.parseInt(st.nextToken());
				remove(n);
				continue;
			} else if (command.equals("all")) {
				all();
				continue;
			} else if (command.equals("toggle")) {
				int n = Integer.parseInt(st.nextToken());
				toggle(n);
				continue;
			} else if (command.equals("empty")) {
				empty();
				continue;
			}
			
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
