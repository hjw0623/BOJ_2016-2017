package heroes.snu.problem;

import java.util.Scanner;

public class BOJ_14710 {

	public static void main(String[] args) {

		Scanner sc= new Scanner (System.in);
		int HH = sc.nextInt();
		int MM = sc.nextInt();
		//시침이 정각에 있을 때 분침의 각도는 0이다 
		if(HH%60==0 && MM==0){
			System.out.println("O");
		}else if (HH%60>0){ //분침이 0이 아닌 경우 
			int k = HH%30; //시침의 각도를 60으로 나누면 시간이 된다. k가0이 아니라면 0~29도 : 1시간 의 시간비
			k *= 12; 		//시침의 1도당 분침이 12도 움직인다.
//			System.out.println(k+ " "+(k*12));
			//1 	2 	3 	4 	5 	6	 7	 8 	9 	10	11	 12 
			//30	60  90  120 150 180  210 240 270 300 330 0
			
			//
			if(MM==k)
				System.out.println("O");
			else{
				System.out.println("X");
			}
		}else{
			System.out.println("X");
		}
	}
}
