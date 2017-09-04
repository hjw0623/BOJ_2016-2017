package basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2908 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer (br.readLine());
		
		String prev = st.nextToken();
		int transPrev =  Integer.parseInt(""+prev.charAt(2)+prev.charAt(1)+prev.charAt(0));
		String next = st.nextToken();
		int transNext = Integer.parseInt(""+next.charAt(2)+next.charAt(1)+next.charAt(0));
		
		System.out.println(Math.max(transPrev, transNext));
				
		
	}

}
