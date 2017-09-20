package solution4;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
	public static long solution(int arr[]) {
		long ret = 0;
		// 두 자리 자연수
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		// 한 자리 자연수
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		int tmpMax = 0;
		// 앞자리가 큰 순서대로 가져다 붙인다.
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 10)
				list2.add(arr[i]);
			else
				list1.add(arr[i]);
		}
		Collections.sort(list1);
		Collections.sort(list2);
		// 비교
		int i = 0;
		int result[] = new int[arr.length];

		// 둘 다 0 아닌 경우
		if (list1.size() != 0 && list2.size() != 0) {
			while (i < result.length) {
				// 한자리수 제일 큰 값부터 비교
				while (!list2.isEmpty()) {
					int j = list2.size() - 1;
					while (!list1.isEmpty()) {
						int k = list1.size() - 1;
//						System.out.println(list2.get(j) + "와 " + list1.get(k) + "를 비교 ");
						int comp = list1.get(k) % 10;
						// 만약 한자리수가 비교값보다 더 크면 result배열에 그 값을 넣고 j--
						if (list2.get(j) > comp) {
//							System.out.println("case 1 result[" + i + "]에 " + list2.get(j) + "를 넣는다 ");
							result[i] = list2.get(j);
							i++;
							list2.remove(j);
							break;
						} else { // 두 자릿수 첫 숫자가 한자릿수보다 더 크면 result에 그 값을 넣고 k--
//							System.out.println("case 2 result[" + i + "]에 " + list1.get(k) + "를 넣는다 ");
							result[i] = list1.get(k);
							list1.remove(k);
							i++;
							continue;
						}
					}
					if (list1.isEmpty() || list2.isEmpty()) {
						break;
					}
				}
				// 남은 배열 몰아주기
				if (list1.size() == 0) {
					while (!list2.isEmpty()) {
						result[i] = list2.get(list2.size() - 1);
						list2.remove(list2.size() - 1);
						i++;
					}
				}else{
					while(!list1.isEmpty()){
						result[i] = list1.get(list1.size()-1);
						list1.remove(list1.size()-1);
						i++;
					}
				}
			}
		} else if (list1.size() == 0) {
			for (i = 0; i < result.length; i++) {
				result[i] = list2.get(result.length - i - 1);
			}
		} else {
			for (i = 0; i < result.length; i++) {
				result[i] = list1.get(result.length - i - 1);
			}
		}
		StringBuilder max = new StringBuilder();
		StringBuilder min = new StringBuilder();

		for (i = 0; i < result.length; i++) {
			max.append(result[i]);
		}
		long maxValue = Long.parseLong(max.toString());

		for (i = result.length - 1; i >= 0; i--) {
			min.append(result[i]);
		}
		long minValue = Long.parseLong(min.toString());
//		System.out.println("max : " + maxValue + ", min: " + minValue);
		return maxValue + minValue;
	}

	public static void main(String[] args) {

		int arr[] = { 1, 2, 3 };
		int arr2[] = { 2, 9, 10, 21, 24 };
		System.out.println(solution(arr));
		System.out.println(solution(arr2));
	}

}
