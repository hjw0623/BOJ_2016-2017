package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11656 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String ss = br.readLine();
		String list[] = new String[ss.length()];
		for(int i=0; i<list.length; i++){
			list[i] = ss.substring(i, ss.length());
		}
		Arrays.sort(list);
		for(int i=0; i<list.length; i++){
			System.out.println(list[i]);
		}
	}

}
