package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_5622 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		char arr[] = br.readLine().toCharArray();
		HashMap<Character, Integer> map = new HashMap<>();
		//preprocessor
		map.put('A', 3);
		map.put('B', 3);
		map.put('C', 3);
		
		map.put('D', 4);
		map.put('E', 4);
		map.put('F', 4);
		
		map.put('G', 5);
		map.put('H', 5);
		map.put('I', 5);
		
		map.put('J', 6);
		map.put('K', 6);
		map.put('L', 6);
		
		map.put('M', 7);
		map.put('N', 7);
		map.put('O', 7);
		
		map.put('P', 8);
		map.put('Q', 8);
		map.put('R', 8);
		map.put('S', 8);

		map.put('T', 9);
		map.put('U', 9);
		map.put('V', 9);
		
		map.put('W', 10);
		map.put('X', 10);
		map.put('Y', 10);
		map.put('Z', 10);
		int totalTime = 0;
		for(int i=0; i<arr.length; i++){
			totalTime += map.get(arr[i]);
		}
		System.out.println(totalTime);
	}
}
