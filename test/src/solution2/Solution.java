package solution2;

public class Solution {
	public static int solution(String dartResult) {
		int answer = 0;
		int current = 0;
		int arr[] = new int[3];
		int countInt = 0;
		for (int i = 0; i < dartResult.length(); i++) {
			// 점수 판독
			// 10 판
			if (i < dartResult.length() - 2 && dartResult.charAt(i) == '1' && dartResult.charAt(i + 1) == '0') {
				String temp = dartResult.charAt(i) + "" + dartResult.charAt(i + 1);
				current = Integer.parseInt(temp);
				arr[countInt] = current;
				i = i+1;
				countInt++;
			}
			// 0~9판독
			else if (dartResult.charAt(i) >= '0' && dartResult.charAt(i) <= '9') {
				String temp = "" + dartResult.charAt(i);
				current = Integer.parseInt(temp);
				arr[countInt] = current;
				countInt++;
			}
			// S, D, T 판독 S는 생략
			else if (dartResult.charAt(i) == 'S')
				continue;
			else if (dartResult.charAt(i) == 'D') {
				arr[countInt - 1] = (int) Math.pow(arr[countInt - 1], 2);
			}else if(dartResult.charAt(i) =='T'){
				arr[countInt - 1] = (int) Math.pow(arr[countInt - 1], 3);
			}
			else if(dartResult.charAt(i) =='*'){
//				System.out.println("스타 ");
				for(int k=countInt-1; k>=countInt-2; k--){
//					System.out.println("k :"+k);
					if(k<0)break;
					
					arr[k] = arr[k]*2;
				}
			}else if(dartResult.charAt(i) =='#'){
//				System.out.println("아차");
				int tmp = arr[countInt-1]*2;
//				System.out.println("아차상으로 -1 곱 :"+ tmp);
				 arr[countInt-1] -= tmp;
//				System.out.println("아차상 적용 후 :"+arr[countInt-1]);
			}
		}
		for(int i=0; i<3; i++){
//			System.out.print("arr[i] : "+arr[i]);
			answer += arr[i];
		}
//		System.out.println();
		return answer;
	}

	public static void main(String[] args) {
		String s1 = "1S2D*3T"; // 37
		String s2 = "1D2S#10S"; // 9
		String s3 = "1D2S0T"; // 3
		String s4 = "1S*2T*3S"; // 23
		String s5 = "1D#2S*3S"; // 5
		String s6 = "1T2D3D#"; // -4
		String s7 = "1D2S3T*"; // 59

		System.out.println(solution(s1));
		System.out.println(solution(s2));
		System.out.println(solution(s3));
		System.out.println(solution(s4));
		System.out.println(solution(s5));
		System.out.println(solution(s6));
		System.out.println(solution(s7));

	}

}
