package heroes.basic.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1032 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		//길이가 같으므로, list에 첫 입력값의 문자를 넣는다. 
		ArrayList<Character> list = new ArrayList<>();
		String input = br.readLine();
		char[] arr = input.toCharArray();
		for(int j=0; j<arr.length; j++){
			list.add(arr[j]);
		}
		//이후의 문자열의 문자들과 인덱스를 비교해가면서 공통되면 유지, 아니면 ?로 교체 
		for (int i = 1; i < number; i++) {
			input = br.readLine();
			arr = input.toCharArray();
			for(int j=0; j<arr.length; j++){
				if(list.get(j)==arr[j]){
					continue;
				}else{
					list.remove(j);
					list.add(j, '?');
				}
			}
		}
		//출력 
		for(int i=0; i<list.size(); i++){
			System.out.print(list.get(i));
		}
	}
}
