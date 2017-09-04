package basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1152 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String word;
		int count =0;
		StringTokenizer st = new StringTokenizer(line);
		while(st.hasMoreTokens()){
			count++;
			word = st.nextToken();
		}
		System.out.println(count);
	}

}
