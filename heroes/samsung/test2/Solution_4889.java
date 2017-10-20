package heroes.samsung.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//문자열의 길이가 최대 2000 --> 비트마스크는 무리 
public class Solution_4889 {
	static int cnt = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;
		while (true) {

			String str = br.readLine();
			if (str.charAt(0) == '-')
				break;

			char[] arr = str.toCharArray();
			boolean[] check = new boolean[arr.length];

			if (!isStable(arr)) { // 올바른 괄호 문자열이 아니라면
				Stack<Integer> pair1 = new Stack<>(); // { 의 인덱스를 담는다
				Stack<Integer> pair2 = new Stack<>(); // } 의 인덱스를 담는다.
				for (int i = 0; i < arr.length; i++) {
					if (arr[i] == '{') {
						pair1.push(i);
					}
					if (arr[i] == '}') {
						if (!pair1.isEmpty()) {
							pair1.pop();
						} else { // } 초과분을 저장
							pair2.push(i);
						}
					}
				}
				// 둘다 남아있다면 어차피 가운데 합치되는 것은 다 빠지고 }}} {{{{{ 이런 모양
				// 이 때 자기자신의 size/2만큼은 자체적으로 바꿔주는게 더 빠르다 ex }}}} -> {}{}, 
				// }}} -> {}}
				// 그리고 그 짝 만큼을 스택에서 빼준다.
				if (!pair2.isEmpty() && !pair1.isEmpty()) {
					if (pair1.size() > 1) {
						cnt += pair1.size() / 2;
					}
					if (pair2.size() > 1) {
						cnt += pair2.size() / 2;
					}
					int deduct1 = (pair1.size() / 2) * 2; // 짝수개면 그 짝만큼 빠지고 0개
															// 남기고, 홀수개면 1개를 남길
															// 것
					int deduct2 = (pair2.size() / 2) * 2;
					for (int d = 0; d < deduct1; d++) {
						pair1.pop();
					}
					for (int d = 0; d < deduct2; d++) {
						pair2.pop();
					}
				}
				// 둘 다 1개만 남았다면
				if (pair1.size() == 1 && pair2.size() == 1) {
					cnt += 2;
					pair1.pop();
					pair2.pop();
				}

				// 만약 둘 중 하나가 비어있고 나머지 한개만 있다면
				if (pair1.isEmpty() && !pair2.isEmpty()) {
					cnt += pair2.size() / 2;
				} else if (pair2.isEmpty() && !pair1.isEmpty()) {
					cnt += pair1.size() / 2;
				}

			}
			System.out.println(t + ". " + cnt);
			t++;
			cnt = 0;
		}
	}

	public static boolean isStable(char[] str) {
		boolean ret = false;
		Stack<Character> stack = new Stack<>();

		if (str.length == 2) {
			if (str[0] == '{' && str[1] == '}') {
				return true;
			} else {
				return false;
			}
		}
		// 올바른 괄호 문자열 검사
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '}') {
				if (!stack.isEmpty())
					stack.pop();
				else {
					return ret = false;
				}
			} else if (str[i] == '{') {
				stack.push(str[i]);
			}
		}
		if (stack.isEmpty()) {
			ret = true;
		} else {
			ret = false;
		}
		return ret;
	}
}
