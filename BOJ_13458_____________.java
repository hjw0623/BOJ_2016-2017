import java.util.Scanner;

public class Main {
	static int n;
	static int a[];
	static int b, c;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		a = new int [n];
		for(int i=0; i<n;i++){
			a[i] = sc.nextInt();
		}
		b = sc.nextInt();
		c = sc.nextInt();
		long result=0;
		for(int i=0; i<n; i++){
			a[i]= a[i]-b;
			result++;
			if(a[i]<0)continue;
			
			if(a[i]%c==0){
				result+= a[i]/c;
			}
			else{
				result +=(a[i]/c)+1;
			}
		}
		System.out.println(result);
	}
}