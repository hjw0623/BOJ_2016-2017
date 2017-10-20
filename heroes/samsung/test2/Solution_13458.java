package heroes.samsung.test2;

import java.util.Scanner;

//총 N개의 시험장이 있고, 각각의 시험장마다 응시자들이 있다. i번 시험장에 있는 응시자의 수는 Ai명이다.
//
//감독관은 총감독관과 부감독관으로 두 종류가 있다. 총감독관은 한 방에서 감시할 수 있는 응시자의 수가 B명이고, 
// 부감독관은 한 방에서 감시할 수 있는 응시자의 수가 C명이다.
//
//각각의 시험장에 총감독관은 오직 1명만 있어야 하고, 부감독관은 여러 명 있어도 된다.
//
//각 시험장마다 응시생들을 모두 감시해야 한다. 이 때, 필요한 감독관 수의 최소값을 구하는 프로그램을 작성하시오.
public class Solution_13458 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		int arr[] = new int [test];
		
		for(int t=0; t<test; t++){
			arr[t] = sc.nextInt();
		}
		int MAIN = sc.nextInt();
		int SUB = sc.nextInt();
		//총  감독은 반드시 한방에 한명씩 있어야 한다. 
		long cnt =0;
		for(int i=0; i<test; i++){
			arr[i]-= MAIN;
			if(arr[i]>0){
				if(arr[i]%SUB!=0){
					cnt +=  (int) (arr[i]/SUB) +1;
				}else{
					cnt+=(int)(arr[i]/SUB);
				}
			}
		}	
		cnt+=test;
		System.out.println(cnt);
	}
}
