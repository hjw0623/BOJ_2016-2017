package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//그룹단어 찾기 
/*
 * 그룹 단어란 단어에 존재하는 모든 문자에 대해서,
 *  각 문자가 연속해서 나타나는 경우만을 말한다. 
 *  예를 들면, ccazzzzbb는 c, a, z, b가 모두 연속해서 나타나고, 
 *  kin도 k, i, n이 연속해서 나타나기 때문에 그룹 단어이지만,
 *   aabbbccb는 b가 떨어져서 나타나기 때문에 그룹 단어가 아니다.
 */
public class BOJ_1316 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		int groupWord = 0;
		for (int k = 0; k < number; k++) {
			boolean checked[] = new boolean[26];
			boolean isGroupWord = true;
			String s = br.readLine();
			for (int i = 0; i < s.length() - 1; i++) {
				// 이미 체크한 알파벳이 뒤에 또 오는 경우
				if (checked[s.charAt(i) - 'a'] || checked[s.charAt(i + 1) - 'a']) {
					isGroupWord = false;
				}
				if (s.charAt(i) == s.charAt(i + 1)) {
					continue;
				} else {
					checked[s.charAt(i) - 'a'] = true;
				}
			}
			if (isGroupWord) {
				groupWord++;
			}
//			for (int i = 0; i < checked.length; i++) {
//				System.out.print(checked[i] + " ");
//			}
		}
		System.out.println(groupWord);
	}

}
