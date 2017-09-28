package heroes.basic.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_11650 {
	static class Point {
		int x; int y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
			
		}
	}
	public static class PointComp implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2){
			if(p1.x < p2.x){
				return -1;
			}else if(p1.x==p2.x){
				if(p1.y<p2.y){
					return -1;
				}else return 1;
			}
			return 1;
		}
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		int x=0,y=0;
		ArrayList<Point> list = new ArrayList<Point>(num);
		for(int i=0;i<num; i++){
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			list.add( new Point(x,y));
		}
		Collections.sort(list, new PointComp());
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i).x+" "+list.get(i).y);
		}
	}
}
