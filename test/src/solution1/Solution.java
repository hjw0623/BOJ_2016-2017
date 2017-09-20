package solution1;

public class Solution {

	public static String[] solution(int n, int[] arr1, int[] arr2) {

		String ans[] = new String[n];
		int ret[] = new int [n];
		
		for(int i=0; i<arr1.length; i++){
			int temp = arr1[i] | arr2[i];
			ret[i] = temp;
		}
		for(int i=0; i<ret.length; i++){
			String s = Integer.toBinaryString(ret[i]);
			if(s.length()<n){
				int aux = n - s.length();
				for(int j=0; j<aux; j++){
					s = "0"+s;
				}
			}
			StringBuilder sb = new StringBuilder();
			
			for(int j=0; j<s.length(); j++){
				if(s.charAt(j)=='0'){
					sb.append(" ");
				}else if(s.charAt(j)=='1'){
					sb.append("#");
				}
			}
			ans[i] = sb.toString();
		}
		return ans;
	}

	public static void main(String[] args) {
		int arr1[] = { 9, 20, 28, 18, 11 };
		int arr2[] = { 30, 1, 21, 17, 28 };
		int n = 5;
		
		String ans[] = solution(5, arr1, arr2);
		for(int i=0; i<ans.length; i++){
			
			System.out.print(i+"th "+ans[i]+" ");
		}
	}

}
