package basic.search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 암벽등반
 https://www.acmicpc.net/problem/2412
 */

class Point implements Comparable<Point> {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if (this.y < o.y) {
			return -1;
		} else if (this.y == o.y) {
			if (this.x < o.x) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}

public class BOJ_2412 {
	static int N, T;
	static Point[] arr; // N <= 50000;
	static boolean[] visited;
	static int ret = -1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		T = sc.nextInt();
		arr = new Point[N + 1];
		visited = new boolean[N + 1];
		Point start = new Point(0, 0);

		arr[0] = start;

		for (int i = 1; i <= N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			Point p = new Point(x, y);
			arr[i] = p;
		}
		Arrays.sort(arr);
		Move(arr);
		System.out.println(ret);
	}

	public static void Move(Point[] arr) {
		Queue<Point> q = new LinkedList<>();
		q.add(arr[0]);

		while (!q.isEmpty()) {
			// level별 큐 탐색
			int size = q.size();
			ret++;
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				int curX = cur.x;
				int curY = cur.y;
				if (curY == T) {
					break;
				}
				// 이진 탐색 트리로 분석 한다.
				int startIdx = BinarySearch(new Point (cur.x, cur.y-2<0? 0: cur.y-2));

				for (int k = startIdx; k <= N; k++) {
					int nextX = arr[k].x;
					int nextY = arr[k].y;
					
					// x값 차이가 2보다 큰 경우에는 다음것을 탐색 
					if (Math.abs(curX - nextX) > 2)
						continue;
					// y값 차이가 2보다 크면 탐색의 의미가 없다. 탈출.
					if (Math.abs(curY - nextY) > 2)
						break;
					if (visited[k])
						continue;
					q.add(arr[k]);
					visited[k] = true;
				}
			}

		}
	}

	public static int BinarySearch (Point currentPoint ){
		int target = currentPoint.y;
		int subTarget = currentPoint.x;
		
		int left =0;
		int right = arr.length-1;
		int mid = left;
		
		while(right>=left){
			mid = (left+right)/2;
			if(arr[mid].y == target){
				//x좌표도 맞는지 확인해볼 필요가 있다.
				while(right>=left){
					if(arr[mid].x == subTarget){
						return mid;
					}
					else if(arr[mid].x < subTarget){
						left = mid+1;
					}else{
						right = mid-1;
					}
					return left;
				}	
			}
			if(arr[mid].y < target) {
				left = mid+1;
			}else{
				right = mid-1;
			}
		}
		return left;
	}
	// public static void printPtr() {
	// for (int i = 0; i < arr.length; i++) {
	// System.out.println(arr[i].x + " " + arr[i].y);
	// }
	// }

}
