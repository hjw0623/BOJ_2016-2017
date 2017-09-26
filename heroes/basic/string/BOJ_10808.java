package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10808 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		char arr[] = br.readLine().toCharArray();
		int count[] = new int[26];
		for(int i=0; i<arr.length; i++){
			count[arr[i]-' '-65]++;
		}
		for(int i=0; i<count.length-1; i++){
			System.out.print(count[i]+" ");
		}System.out.print(count[count.length-1]);
	}
}
