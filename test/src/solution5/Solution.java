package solution5;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

	public static int solution(String str1, String str2) {
		int answer = 0;
		String strs1 = str1.toUpperCase();
		String strs2 = str2.toUpperCase();
//		StringBuilder sb1 = new StringBuilder();
//		StringBuilder sb2 = new StringBuilder();
		
		
		HashMap<String, Integer> ss1 = new HashMap<>();
		HashMap<String, Integer> ss2 = new HashMap<>();

		for (int i = 0; i < strs1.length() - 1; i++) {
			// 만약 문자 두개면 해시맵에 넣는다.
			String input="";
//			System.out.println("ss: "+strs1.charAt(i)+""+strs1.charAt(i+1));
			if ((strs1.charAt(i) >=65) && strs1.charAt(i)<=90
					&& strs1.charAt(i+1)>=65 &&(strs1.charAt(i + 1) <= 90)) {
				 input = strs1.charAt(i) + "" + strs1.charAt(i + 1);
				// 중복된 경우,count+1
//				 System.out.println("input : "+ input);
//				 System.out.println(strs1.charAt(i + 1)-0);
				 
				if (ss1.containsKey(input)) {
					int count = ss1.get(input) + 1;
//					System.out.println(count);
					ss1.put(input, count);
				} else {
					ss1.put(input, 1);
				}
			} 
			//만약 하나가 알파벳이 아니라면 알파벳 찾을 때까지 인덱스를 증가시킨다. 
			else {
				continue;

			}
		}
		ArrayList<String> list1Key = new ArrayList<>(ss1.keySet());
		ArrayList<Integer> list1Value = new ArrayList<>(ss1.values());
		
		
		
		for (int i = 0; i < strs2.length() - 1; i++) {
			// 만약 문자 두개면 해시맵에 넣는다.
			String input="";
//			System.out.println("ss: "+strs1.charAt(i)+""+strs1.charAt(i+1));
			if ((strs2.charAt(i) >=65) && strs2.charAt(i)<=90
					&& strs2.charAt(i+1)>=65 &&(strs2.charAt(i + 1) <= 90)) {
				 input = strs2.charAt(i) + "" + strs2.charAt(i + 1);
				// 중복된 경우,count+1
//				 System.out.println("input : "+ input);
//				 System.out.println(strs2.charAt(i + 1)-0);
				 
				if (ss2.containsKey(input)) {
					int count = ss2.get(input) + 1;
//					System.out.println(count);
					ss2.put(input, count);
				} else {
					ss2.put(input, 1);
				}
			} 
			//만약 하나가 알파벳이 아니라면 알파벳 찾을 때까지 인덱스를 증가시킨다. 
			else {
				continue;

			}
		}
		ArrayList<String> list2Key = new ArrayList<>(ss2.keySet());
		ArrayList<Integer> list2Value = new ArrayList<>(ss2.values());
		
//		System.out.println("ss1 size :"+ss1.size());
//		for(int i = 0; i<list1Key.size(); i++){
//			System.out.println("key of 1st: "+list1Key.get(i));
//		}
//		for(int k=0; k<list1Value.size(); k++){
//			System.out.println("value of 1st :"+list1Value.get(k));
//		}
//		System.out.println("=====");
//		System.out.println("ss2 size :"+ss2.size());
//
//		for(int i = 0; i<list2Key.size(); i++){
//			System.out.println("key of 2nd: "+list2Key.get(i));
//		}
//		for(int k=0; k<list1Value.size(); k++){
//			System.out.println("value of 2nd :"+list2Value.get(k));
//		}
//		System.out.println("===========");
		int subSum1 = 0;
		int subSum2 = 0;
		for(int i=0; i<list1Value.size(); i++){
			subSum1 += list1Value.get(i);
		}
		for(int i=0; i<list2Value.size(); i++){
			subSum2 += list2Value.get(i);
		}
		int And = 0; //교집합 개수 
		for(int i=0; i<list2Key.size(); i++){
			String comp = list2Key.get(i);
			for(int j=0; j<list1Key.size(); j++){
				if(comp.equals(list1Key.get(j))){
					And += Math.min(list2Value.get(i), list1Value.get(j));
				}
			}
		}
		int totalSum = subSum1+subSum2 - And;
		if(totalSum<=0){
			answer = 65536;
//			System.out.println("ans: "+answer);

			return 65536; 
		}
		answer = (int) (And*65536)/totalSum;
		
//		System.out.println("ans: "+answer);
				
		
		return answer;
	}

	public static void main(String[] args) {
		String s1 = "FRANCE";
		String ss1 = "french";
		String s3 = "aa1+aa2";
		String ss3 = "AAAA12";
		String s4 = "E=M*C^2";
		String ss4 = "e=m*c^2";
		System.out.println(solution(s1, ss1));
		System.out.println(solution(s3, ss3));
		System.out.println(solution(s4, ss4));

	}

}
