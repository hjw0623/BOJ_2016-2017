package heroes.basic.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//정수 N개로 이루어진 배열 A가 주어진다. 상근이는 수열의 수 하나를 골라서 값을 1 감소시킬 수 있다.
//단, 숫자는 1보다 작아질 수 없다.
//상근이는 위의 감소시키는 연산을 최대 T번 하려고 한다. 
//이 때, 인접한 숫자의 차이의 최대값을 최소로 하는 프로그램을 출력

//이분탐색 기본 구현 문제. 익숙해질 것 

public class BOJ_3090 {
	static class Element {
		int diff_prev_cur;
		int current;
		int diff_next_cur;
		int idx; // element노드의 현재 위치
		int visited;

		Element(int diff1, int c, int diff2, int i, int v) {
			this.diff_prev_cur = Math.abs(diff1);
			this.current = c;
			this.diff_next_cur = Math.abs(diff2);
			this.idx = i;
			this.visited = v;
		}

		// 배열로부터 값을 갱신한다
		public void update() {
			if (this.idx != 0) {
				diff_prev_cur = Math.abs(arr[idx - 1] - arr[idx]);
			}
			if (this.idx != arr.length - 1) {
				diff_next_cur = Math.abs(arr[idx] - arr[idx + 1]);
			}
			current = arr[idx];
		}
	}

	static class ElementComparator implements Comparator<Element> {
		@Override
		public int compare(Element o1, Element o2) {
			if (o1.diff_next_cur + o1.diff_prev_cur > o2.diff_next_cur + o2.diff_prev_cur)
				return -1;
			else if (o1.diff_next_cur + o1.diff_prev_cur == o2.diff_next_cur + o2.diff_prev_cur) {

				if (o1.visited < o2.visited) {
					return -1;
				} else
					return 1;
			} else
				return 1;
		}

	}

	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken()); // 감소연산 최대 횟수
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			System.out.println(arr[i]);
		}
		PriorityQueue<Element> pq = new PriorityQueue<Element>(1, new ElementComparator());
		// 양 끝 두 원소 입력
		pq.add(new Element(0, arr[0], arr[0] - arr[1], 0, 0));
		pq.add(new Element(arr[arr.length - 2] - arr[arr.length - 1], arr[arr.length - 1], 0, arr.length - 1, 0));

		for (int i = 1; i < arr.length - 1; i++) {
			// 이전 차이, 현재 값, 다음 차이, 인덱스
			pq.add(new Element(arr[i] - arr[i - 1], arr[i], arr[i] - arr[i + 1], i, 0));
		}

		while (T > 0) {
			Element current = pq.peek();
			System.out.println("index : " + current.idx + ", 원소값 :" + current.current + ", 양옆차 : "
					+ (current.diff_next_cur + current.diff_prev_cur));
			// 갱신 시도
			if (arr[current.idx] > 1) {
				Element revise = pq.poll();
				int currentDiff = revise.diff_next_cur + revise.diff_prev_cur;
				arr[current.idx]--;
				T--;
				revise.update();
				if (revise.diff_next_cur + revise.diff_prev_cur < currentDiff) {
					revise.visited++;
					pq.add(revise);
				}
				// else{
				// System.out.println("개선 취소 ");
				// arr[current.idx]++;
				// revise.update();
				// revise.visited++;
				// pq.add(revise);
				// T++;
				// }
			}
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
