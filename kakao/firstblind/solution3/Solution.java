package kakao.firstblind.solution3;

import java.util.HashMap;

public class Solution {

	public static int solution(int cacheSize, String[] cities) {
		int answer = 0;
		int LRU[] = new int [cacheSize];
		String LRU2[] = new String[cacheSize];
		
		if(cacheSize ==0){
			return cities.length*5;
		}
		//init 
		for(int i = 0; i<LRU.length; i++){
			LRU[i] = -100_001;
			LRU2[i] = "";
		}
		for(int i=0; i<cities.length; i++){
			//5 증가.
			String findString = cities[i].toLowerCase();
			boolean findout = false;
			for(int j=0; j<LRU2.length; j++){
				//if hitted
				if(findString.equals(LRU2[j])){
					LRU[j] = 0;
					findout = true;
					answer +=1;
					break;
				}else{
					LRU[j] -=1;
				}
			}

			//못 찾았다면 페이지 교체할 인덱스를 찾는다. 
			if(!findout){
				int tmpMin = LRU[0];
				int index = 0;
				for(int j=0; j<LRU.length; j++){
					if(tmpMin > LRU[j]){
						tmpMin = LRU[j];
						index = j;
					}
				}
				LRU2[index] = findString;
				LRU[index] = 0;
				answer +=5;
			}
		}
//		System.out.println("---");

	      return answer;
	  }

	public static void main(String[] args) {

		int cacheSize1 = 3;
		String cities1[] = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA" };
		int cacheSize2 = 3;
		String cities2[] = { "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" };
		int cacheSize3 = 2;
		String cities3[] = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome" };
		int cacheSize4 = 5;
		String cities4[] = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome" };
		int cacheSize5 = 2;
		String cities5[] = { "Jeju", "Pangyo", "NewYork", "newyork" };
		int cacheSize6 = 0;
		String cities6[] = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA" };

		System.out.println(solution(cacheSize1, cities1));
		System.out.println(solution(cacheSize2, cities2));
		System.out.println(solution(cacheSize3, cities3));
		System.out.println(solution(cacheSize4, cities4));
		System.out.println(solution(cacheSize5, cities5));
		System.out.println(solution(cacheSize6, cities6));

	}

}
