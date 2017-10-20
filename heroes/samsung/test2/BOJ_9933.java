package heroes.samsung.test2;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ_9933 {
	public static void main(String[] args) {

		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine(); //빈 문자열 버퍼 비우기 
		HashMap<String, String> map = new HashMap<>();
		String result =""; //암호 결과값 
		
		for(int i=1; i<=N; i++){
			String tmp = sc.nextLine(); //입력 문자열 
			String rev = reverse(tmp);  //역순 문자열 
			if(!map.containsKey(rev)){	//입력의 역순이 존재하지 않으면 
				map.put(rev	, tmp);		//역순 문자열을 key로 입력 
			}
			if(map.containsKey(tmp)){	//입력 문자열이 존재하면 
				result = map.get(tmp);
			}
		}
		int mid = (result.length())/2;
		System.out.println(result.length()+" "+result.charAt(mid));
	}
	//문자열 역순 만들기 
	public static String reverse(String str){
		return new StringBuffer(str).reverse().toString();
	}
}
