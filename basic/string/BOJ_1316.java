package basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1316 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int alpha[] = new int[26];
		char curr, next;
		int noGroupWord = 0;
		//매 단어별
		for(int i=0; i<count; i++){
			Arrays.fill(alpha, 0);
			String input = br.readLine();
			char line[] = input.toCharArray();
			//문자열의 문자 비교 

			for(int j=0; j<line.length-1; j++){
				curr = line[j];
				next = line[j+1];
				
				if(alpha[curr-'a']==0){
					alpha[curr-'a']=1;
				}
				
				if(curr == next){
					continue;
				}
				
				if(curr != next){
					if(alpha[next-'a']!=0){
						noGroupWord++;
						break;
					}
				}
			}
		}
		System.out.println(count - noGroupWord);
	}
}
