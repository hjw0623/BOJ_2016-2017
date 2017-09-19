package basic.dp;

public class test4 {

	public static void main(String[] args) {

		int arr[][] = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
		System.out.println(solution(arr));

	}

	public static int solution(int[][] board) {
		int maxSize = board.length > board[0].length ? board[0].length : board.length;
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j]==0) continue;
				//if board[i][j]==1
				int maxX = 1;
				int maxY = 1;
				for(int k=j; k<board[i].length; k++){
					if(board[i][k]==0){
						
					}
				}
				
			}
		}
		int answer = 1234;

		return answer;
	}
}
