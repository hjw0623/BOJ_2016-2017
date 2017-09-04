package basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2675 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st ; 
		StringBuilder sb;
		for(int i=0; i<testCase; i++){
			st= new StringTokenizer(br.readLine());
			int repeat = Integer.parseInt(st.nextToken());
			char s [] =st.nextToken().toCharArray();
			sb = new StringBuilder();
	
			for(int j=0; j<s.length; j++){
				char cur = s[j];
				for(int k=0; k<repeat; k++){
					sb.append(cur);
				}
			}
			System.out.println(sb.toString());
		}
	}
}
