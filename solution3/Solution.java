package solution3;

public class Solution {
	public static int solution(int input) {
		String compare = "" + input;
		// System.out.println("input : "+input);
		boolean checkRevPlus = true;
		int count = 0; // 연산 횟수
		while (count <= 3) {
			checkRevPlus = true;
			// System.out.println(compare.length());
			// 현재 숫자가 Rev_plus인지 검사한다
			for (int i = 0; i < compare.length() / 2; i++) {

				if (compare.charAt(i) != compare.charAt(compare.length() - i - 1)) {
					checkRevPlus = false;
				}
			}
			// 맞으면 탈출
			if (checkRevPlus) {
				return Integer.parseInt(compare);
			} else {
				count++;
				// 현재 숫자
				int current = Integer.parseInt(compare);
				StringBuilder sb = new StringBuilder();
				// 문자열 뒤집기
				for (int i = compare.length() - 1; i >= 0; i--) {
					sb.append(compare.charAt(i));
				}
				String reverse = sb.toString();
				int RevIncrement = Integer.parseInt(reverse);

				int next = current + RevIncrement;

				compare = "" + next;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(solution(78));
		System.out.println(solution(57));
	}

}
