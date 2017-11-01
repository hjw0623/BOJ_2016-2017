package basic.KMP;

public class KMPSubStringSearch {

	//d 배열생성
	private int[] computeTemporaryArray(char pattern[]){
		int [] lps = new int[pattern.length]; //가장 긴 패턴 부분문자열 길이 저장 배열 
		int index = 0;
		for(int i=1; i<pattern.length;){
			if(pattern[i] ==pattern[index]){
				lps[i] = index+1;
				index++;
				i++;
			}else{
				if(index!=0){//j!=0이면 보정 가능 
					index = lps[index-1];
				}else{
					lps[i] =0;
					i++;
				}
			}
		}
		return lps;
	}
	//KMP 알고리즘 
	public boolean KMP(char[]text, char [] pattern){
		int lps[] = computeTemporaryArray(pattern);
		int i=0; 
		int j=0;
		while(i<text.length && j<pattern.length){
			if(text[i]==pattern[j]){
				i++;
				j++;
			}else{
				if(j!=0){
					j = lps[j-1];
				}else{
					i++;
				}
			}
		}
		if(j==pattern.length){
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		String str= "abcxabcdabcdabcy";
		String subString = "abcdabcy";
		KMPSubStringSearch kmp = new KMPSubStringSearch();
		boolean result = kmp.KMP(str.toCharArray(), subString.toCharArray());
		System.out.println(result);

	}

}
