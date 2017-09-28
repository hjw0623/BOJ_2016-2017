package heroes.basic.priorityQueue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

//문제 조건상입력값이 최대 1천만번 이므로 제한된 시간내에 O(NlogN)으로도 시간초과가 날 수 있다. 
//Counting Sort를 한다. 
public class BOJ_10989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		BufferedWriter print = new BufferedWriter(new OutputStreamWriter(System.out));
		PrintWriter out = new  PrintWriter(print);
		
		int num = Integer.parseInt(br.readLine());
		int arr[] = new int [num];
		int tmpMax = 0;
		for(int i=0; i<num; i++){
			arr[i] = Integer.parseInt(br.readLine());
			if(tmpMax<arr[i]){
				tmpMax = arr[i];
			}
		}
		int count[] = new int [tmpMax+1];
		int result[] = new int [arr.length+1];
		for(int i=0; i<arr.length; i++){
			count[arr[i]]++;
		}
		//누적 합을 해준다. 
		for(int i=1; i<count.length; i++){
			count[i] = count[i]+count[i-1];
		}
		//정렬된 새로운 배열을 만든다 index : 1 ~ arr.length 까지 사용.
		//정렬되지 않은 배열로부터 값을 읽어서 count배열에서 차감하는 식으로 result 배열에 넣는다.
		for(int i=0; i<arr.length; i++){
			result[count[arr[i]]]= arr[i];
			count[arr[i]]--; //count--
		}
		
		for(int i=1; i<result.length; i++){
			out.println(result[i]);
		}
		print.flush();
		print.close();
	}
}
