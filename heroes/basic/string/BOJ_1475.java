package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1475 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		char [] arr = br.readLine().toCharArray();
		int []count = new int[10]; //0~9
		for(int i=0; i<arr.length; i++){
			count[arr[i]-'0']++;
		}
		int tmpMax = 0;
		int tmpIdx = 0;
		for(int i=0; i<count.length; i++){
			if(tmpMax <count[i]){
				tmpMax = count[i];
				tmpIdx = i;
			}
		}
		if(tmpIdx ==6){
			System.out.println((tmpMax+count[9]+1)/2);
		}else if(tmpIdx ==9){
			System.out.println((tmpMax+count[6]+1)/2);
		}else{
			System.out.println(tmpMax);
		}
	}
}
