package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1157 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String ss = br.readLine().toLowerCase();
		char [] arr = ss.toCharArray();
		
		int []count = new int [26]; //a~z
		for(int i=0; i<arr.length; i++){
			count[arr[i]-' '-65]++;
		}
		int duplicated =0;
		int tmpMax = 0;
		char maxCapital ='A';
		for(int i=0; i<count.length; i++){
			if(count[i]>tmpMax){
				tmpMax = count[i];
				maxCapital = (char)('A'+i);
			}
		}
		//최대값 개수 중복 검사 
		for(int i=0; i<count.length; i++){
			if(tmpMax==count[i]){
				duplicated++;
			}
		}
		if(duplicated>1)
			System.out.println("?");
		else
			System.out.println(maxCapital);
	}
}
