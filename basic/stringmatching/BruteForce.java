package basic.stringmatching;

public class BruteForce {
	//패턴 P가 T문자열의 substring인지 여부를 검토. 
	//주어진 텍스트 길이가 n, 패턴의 길이가 m 
	//비교를 위해 선택 가능한 위치는 n-m+1개 
	//시간복잡도 O((n-m+1)xm) ~~ O(nxm)
	public int BruteForceStringMatch(int T[], int n, int P[], int m){
		for(int i=0; i<=n-m; i++){
			int j = 0;
			while(j<m && P[j] ==T[i+j])
				j = j+1;
			if(j==m)
				return i; //
		}
		return -1; //일치하는 패턴이 없다.
	}
}
