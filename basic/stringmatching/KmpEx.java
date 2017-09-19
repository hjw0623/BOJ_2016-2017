package basic.stringmatching;

import java.util.ArrayList;

public class KmpEx {
	public static void main(String[] args) {
		String strings = "KOIOIOIOIKOIOIKO";
		String pattern = "IOI";
		int startIndex = 0;
		int searchCount = 0;
		ArrayList<Integer> startIndexList = new ArrayList<>();
		KmpAlgorithm kmpAlgorithm = new KmpAlgorithm(pattern.length());
		kmpAlgorithm.fail(pattern);
		kmpAlgorithm.printFail(strings, pattern);

		startIndex = kmpAlgorithm.search(strings, pattern, startIndex);
		if (startIndex == -1) {
			System.out.println("탐색결과 없음.");
		} else {
			System.out.println("타겟의 시작인덱스 : " + startIndex + "\n");
			searchCount++;
			startIndexList.add(startIndex);
			while (startIndex + (pattern.length() - 1) != strings.length()) { // 타겟이
																				// 여러개
																				// 있을
																				// 경우
																				// 중복검사.
				startIndex = kmpAlgorithm.search(strings, pattern, startIndex + 1);
				if (startIndex == -1) {
					break;
				}
				System.out.println("타겟의 시작인덱스 : " + startIndex + "\n");
				searchCount++;
				startIndexList.add(startIndex);
			}
			System.out.println("탐색종료");
			System.out.println("\"" + strings + "\"" + "에서 " + "\"" + pattern + "\" " + searchCount + "개 검색완료. 시작위치 : "
					+ startIndexList);
		}
	}
}
