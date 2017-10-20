package heroes.basic.priorityQueue;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pro_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        String[] data = new String[N];

        for (int i = 0; i < N; i++) {
            data[i] = br.readLine();
        }

        // lamda 식 사용
        Arrays.sort(data, (String o1, String o2) -> {
            int len1 = o1.length();
            int len2 = o2.length();

            if(len1<len2)return -1;
            else if(len1==len2){
            	if(o1.compareTo(o2)<0){
            		return -1;
            	}else 
            		return 1;
            }
            return 1;
        });

     


        sb.append(data[0]).append('\n');
        for (int i = 1; i < N; i++) {
            if(data[i-1].equals(data[i])){
                continue;
            }
            sb.append(data[i]).append('\n');
        }

        System.out.print(sb);
    }
}