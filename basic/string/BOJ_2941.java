package basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2941 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char tmp[] = br.readLine().toCharArray();
		char first, second, third;
		int deduction = 0;
		for(int i=0; i<tmp.length-1; i++){
			System.out.println("current index : "+i);
			first = tmp[i];
			second = tmp[i+1];
			third = 0;
			if(i+2<tmp.length){
				third = tmp[i+2];
			}

			if(first=='c' && (second =='='||second=='-')){
				deduction+=1;
				i++; 
				continue;
			}
			if(first=='d' && second=='z' && third=='='){
				i+=2;
				deduction+=2;
				continue;
			}
			if(first =='d' && second =='-'){
				i++;
				deduction+=1;
				continue;
			}
			if(first=='l' && second =='j'){
				i++;
				deduction+=1;
				continue;
			}
			if(first=='n' && second =='j'){
				i++;
				deduction+=1;
				continue;
			}
			if(first=='s' && second =='='){
				i++;
				deduction+=1;
				continue;
			}
			if(first=='z' && second =='='){
				i++;
				deduction+=1;
				continue;
			}
		}
		System.out.println(tmp.length-deduction);
	}

}
