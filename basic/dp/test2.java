package basic.dp;

public class test2 {

	public static void main(String[] args) {
		int arr[][] = new int [3][2];
		arr[0][0] = 1; arr[0][1] = 1;
		arr[1][0] = 2; arr[1][1] = 2;
		arr[2][0] = 1; arr[2][1] = 2;
		int ans[] = solution(arr);
		System.out.println(ans[0]+" "+ans[1]);
	}
	 public static int[] solution(int[][] v) {
		 	int x = v[0][0]^v[1][0]^v[2][0];
		 	int y = v[0][1]^v[1][1]^v[2][1];

	        int[] answer = {x, y};

	        return answer;
	    }
}	
