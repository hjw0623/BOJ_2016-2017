package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2908 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer( br.readLine());
	
		
		
		int first = Integer.parseInt(st.nextToken());
		int first_first = first/100; //ex 734 -> 7
		int first_second = (first-first_first*100)/10;
		int first_third = (first-first_first*100-first_second*10);
		int newFirst = first_third*100+first_second*10+first_first;

		int second =Integer.parseInt(st.nextToken());
		int second_first = second/100;
		int second_second = (second-second_first*100)/10;
		int second_third = (second-second_first*100-second_second*10);
		int newSecond = second_third*100+second_second*10+second_first;
		
		if(newFirst >newSecond )
			System.out.println(newFirst);
		else 
			System.out.println(newSecond);
	}

}
