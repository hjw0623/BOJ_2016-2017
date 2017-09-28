package heroes.basic.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine().toUpperCase();
		ArrayList<Integer> counter = new ArrayList<>();
		ArrayList<Integer> counter2 = new ArrayList<>();

		for (int i = 0; i < 26; i++) {
			counter.add(0);
		}
		for (int i = 0; i < str.length(); i++) {
			counter.set((int) (str.charAt(i) - 'A'), counter.get((int) (str.charAt(i) - 'A')) + 1);
		}
		for (int i = 0; i < 26; i++) {
			counter2.add(counter.get(i));
		}
		Collections.sort(counter2);
		if (counter2.get(25) == counter2.get(24)) {
			System.out.println("?");
		} else {
			for (int i = 0; i < counter.size(); i++) {
				if (counter.get(i) == counter2.get(25)) {
					System.out.println((char) (i + 'A'));
					break;
				}
			}
		}
	}
}