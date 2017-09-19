package basic.dp;

import java.util.HashMap;

public class test {

	public static void main(String[] args) {
//		System.out.println(solution(98_765_432));
//		System.out.println(solution(100_000_000));
		int arr[] = {4,1,3,2};
		int arr2[] = {4,1,3};
		System.out.println(solution2(arr));
		System.out.println(solution2(arr2));
	}
	
	 public static boolean solution2(int[] arr) {
	        int maxInput = arr.length;
	        boolean check[] = new boolean[arr.length+1];
	        boolean answer = true;

	        for(int i=0; i<arr.length; i++){
	        	
	        	int curInput = arr[i];
	        	if(curInput > maxInput){
	        		return answer = false;
	        	}
	        	if(check[curInput]){
	        		return answer = false;
	        	}
	        	
	        	check[curInput] = true;
	        	
	        }
	        return answer;
	    }
	public static int solution(int n) {
		int answer = 0;
        int power = 10;
        int arr[]  = new int[9];
        for(int i=1; i<= 9; i++){
        	power = (int)Math.pow(10, 1);
        	 arr[i-1] = n%power;
        	 
        	 n = n-arr[i-1];
        	 answer += arr[i-1];
        	 n=n/10;
        }
//        for(int i=0; i<arr.length; i++){
//        	System.out.print(arr[i]+" ");
//        }
//        
		return answer;
	}
}
