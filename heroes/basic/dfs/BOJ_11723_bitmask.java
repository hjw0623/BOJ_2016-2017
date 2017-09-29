package heroes.basic.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11723_bitmask {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer num = Integer.parseInt(st.nextToken());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String command = "";
		int bitmask = 0;
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			command = st.nextToken();
			if (command.equals("all") || command.equals("empty")) {
				if (command.equals("all")) {
					bitmask = (1 << 20) - 1; // 1111 1111 1111 1111 1111 인 상황
				} else {
					bitmask = 0;
				}
			} else {
				int n = Integer.parseInt(st.nextToken())-1;
				if (command.equals("add")) {
					bitmask |= (1 << n); // n에 해당되는 비트에 체크

				} else if (command.equals("check")) {
					if ((bitmask & (1 << n)) == 0) {
						 sb.append(0).append("\n");
					} else {
						 sb.append(1).append("\n");
					}
				} else if (command.equals("toggle")) {
					if ((bitmask & (1 << n)) == 0) {// 없으면 add
						bitmask |= (1 << n);
					} else {// 있으면 remove
						bitmask ^= (1 << n);
					}
				} else if (command.equals("remove")) {
					if ((bitmask & (1 << n)) != 0){
						bitmask = bitmask & ~(1 << n); // n 자리 비트에 0을 넣는다.
					}
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
