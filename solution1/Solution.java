package solution1;

import java.util.Arrays;

//여러 개의 1차원 점을 표준 입력에서 읽은 후, 
//그중 가장 가까운 거리에 있는 두 점의 한쌍을 출력
//입력은 10개 이하, 각각 10자리 이하의 자연수.

public class Solution {
	public static long[] solution(long arr[]){
		long result [] = new long[2];
		Arrays.sort(arr);
		long tempX = 0;
		long tempX2 = 0;
		long tempSub = Long.MAX_VALUE;
		for(int i=0; i<arr.length-1; i++){
			if(arr[i+1]-arr[i]<tempSub){
				tempSub = arr[i+1]-arr[i];
				tempX = arr[i];
				tempX2 = arr[i+1];
			}
		}
		result[0] = tempX;
		result[1] = tempX2;
		return result;
	}
	public static void main(String[] args) {
		long arr[] = {6, 20, 34, 8, 38, 40};
		long ret[] = solution(arr);
		System.out.println(ret[0]+" "+ret[1]);
	}

}
