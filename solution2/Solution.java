package solution2;

import java.util.StringTokenizer;

public class Solution {
	public static int[] solution(String str) {
		int ret[] = new int[2];
		StringTokenizer st = new StringTokenizer(str);
		int moumDual = 0;
		int jaumTriple = 0;
		char[] moumSet = { 'a', 'e', 'i', 'o', 'u' };

		while (st.hasMoreTokens()) {
			String sub = st.nextToken();
			// 모음검사
			for (int i = 0; i < sub.length() - 1; i++) {
				boolean checkFirstMoum = false;
				boolean checkSecondMoum = false;
				for (int j = 0; j < moumSet.length; j++) {
					if (sub.charAt(i) == moumSet[j])
						checkFirstMoum = true;
				}
				// 첫번째 글자가 모음이면 두번째 글자 검사
				if (checkFirstMoum) {
					for (int k = 0; k < moumSet.length; k++) {
						if (sub.charAt(i + 1) == moumSet[k])
							checkSecondMoum = true;
					}
				}
				if (checkSecondMoum) {
					moumDual++;
					break;
				}
			}
			// 자음 3연속 검사
			for (int i = 0; i < sub.length() - 2; i++) {
				boolean checkFirstJaum = true;
				boolean checkSecondJaum = true;
				boolean checkThirdJaum = true;
				// i번째 자음 검사
				for (int j = 0; j < moumSet.length; j++) {
					if (sub.charAt(i) == moumSet[j]) {
						checkFirstJaum = false;
						break;
					}
				}
				// i번째가 모음이면 더 검사할 필요가 없다. 다음 반복문으로 넘어간다.
				if (!checkFirstJaum)
					continue;
				// i+1번째 자음 검사
				for (int j = 0; j < moumSet.length; j++) {
					if (sub.charAt(i + 1) == moumSet[j]) {
						checkSecondJaum = false;
						break;
					}
				}
				// i+1번째가 모음이면 더 검사할 필요가 없다. i=i+1로 갱신 후 다음 반복문으로 넘어간다.
				if (!checkSecondJaum) {
					i = i + 1;
					continue;
				}

				// i+2번쨰 자음 검사
				for (int j = 0; j < moumSet.length; j++) {
					if (sub.charAt(i + 2) == moumSet[j]) {
						checkThirdJaum = false;
						break;
					}
				}
				if(!checkThirdJaum) {
					i= i+2;
					continue;
				}
				if(checkFirstJaum&&checkThirdJaum&&checkSecondJaum){
					jaumTriple++;
					break;
				}
			}
		}
		ret[0] = moumDual;
		ret[1] = jaumTriple;
		return ret;
	}

	public static void main(String[] args) {
		String str = "toast standard bank display book";
		int ret[] = solution(str);

		System.out.println(ret[0]);
		System.out.println(ret[1]);

	}

}
