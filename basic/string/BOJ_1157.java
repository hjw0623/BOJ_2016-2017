package basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1157 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		char tmp[] =line.toCharArray();
		int count[] = new int [26];
		
		for(int i=0; i<tmp.length; i++){
			if(tmp[i]-0 < 91){
				count[tmp[i]-65]+=1;
			}
			else if(tmp[i]-0 >=97 && tmp[i]-0 <=122){
				count[tmp[i]-97]+=1;
			}
		}
		int tmpMax = 0;
		int maxIndex = 0;
		for(int i=0; i<count.length; i++){
			if(tmpMax<count[i]){
				tmpMax = count[i];
				maxIndex = i;
			}	
		}
		int duplicated =0;
		for(int i=0; i<count.length; i++){
			if(tmpMax==count[i])
				duplicated++;
		}
		if(duplicated==1){
			System.out.println((char)(maxIndex+65));
		}else{
			System.out.println("?");
		}
	}

}
