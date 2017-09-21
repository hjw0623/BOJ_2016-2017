package basic.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
class Point {
	public int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Length implements Comparable<Length> {
	public Point first;
	public Point second;
	public double distance;

	public Length(Point f, Point s, double d) {
		this.first = f;
		this.second = s;
		this.distance = d;
	}

	public int compareTo(Length o) {
		if (this.distance > o.distance)
			return -1;
		else
			return 1;
	}
}

public class BOJ_9015 { 
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		for (int t = 0; t < TC; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
//			System.out.println("n :" + n);
			// 0 ~ 10000 20000
			Point coord[] = new Point[n]; // 0~3000
			int listSize = (n * (n - 1)) / 2;
			Length list[] = new Length[listSize];
			for (int k = 0; k < n; k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				coord[k] = new Point(x, y);
			}
			int k = 0;
			for (int i = 0; i < coord.length; i++) {
				for (int j = i; j < coord.length; j++) {
					if (i == j)
						continue;
					double dist = (coord[i].x - coord[j].x) * (coord[i].x - coord[j].x)
							+ (coord[i].y - coord[j].y) * (coord[i].y - coord[j].y);
					dist = Math.sqrt(dist);
//					System.out.println("dist :" + dist);
					list[k] = new Length(coord[i], coord[j], dist);
//					System.out.println(list[k].distance);
					k++;
				}
			}
			Arrays.sort(list);
			double result = 0;
			// 선분 가운데 정사각형 판별
			for (int i = list.length - 4; i >= 0; i--) {
				// 길이가 같은 선분 네개가 존재한다면
				if (list[i].distance == list[i + 1].distance && list[i].distance == list[i + 2].distance
						&& list[i].distance == list[i + 3].distance) {
					// 점이 네개만 존재하면 사각형
					HashMap<Integer, Integer> xCoord = new HashMap<>();
					HashMap<Integer, Integer> yCoord = new HashMap<>();

					for (int w = i; w <= i + 3; w++) {
						
						//x좌표 저장 
						//w 번째 길이의 first x좌표 저장
						if (xCoord.containsKey(list[w].first.x)) {
							int count = xCoord.get(list[w].first.x) + 1;
							xCoord.put(list[w].first.x, count);
						} else {
							xCoord.put(list[w].first.x, 1);
						}
						//w 번째 길이의 second x좌표 저장
						if (xCoord.containsKey(list[w].second.x)) {
							int count = xCoord.get(list[w].second.x) + 1;
							xCoord.put(list[w].second.x, count);
						} else {
							xCoord.put(list[w].second.x, 1);
						}
						
						//y좌표 저장 
						if (yCoord.containsKey(list[w].first.y)) {
							int count = yCoord.get(list[w].first.y) + 1;
							yCoord.put(list[w].first.y, count);
						} else {
							yCoord.put(list[w].first.y, 1);
						}
						//w 번째 길이의 second y좌표 저장
						if (yCoord.containsKey(list[w].second.y)) {
							int count = yCoord.get(list[w].second.y) + 1;
							yCoord.put(list[w].second.y, count);
						} else {
							yCoord.put(list[w].second.y, 1);
						}
					}
					if( xCoord.size() == 4 && yCoord.size()==4){
						result = Math.pow(list[i].distance, 2);
						result = Math.round(result);
					}
				}
			}
			System.out.println((int)result);
		}
	}

}
