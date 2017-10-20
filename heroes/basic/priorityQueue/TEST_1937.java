package heroes.basic.priorityQueue;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TEST_1937 {

    static int N;
    static int [][] bamboo;
    static int [][] date;

    static int [] xD = {0,0,-1,1};
    static int [] yD = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bamboo = new int[N][N];
        date = new int[N][N];

        for (int i = 0; i < N; i++) {
            String [] line = br.readLine().split("\\s");
            for (int j = 0; j < N; j++) {
                bamboo[i][j] = Integer.parseInt(line[j]);
            }
        }
        for(int i=0; i<N; i++){
        	for(int j=0; j<N; j++){
        		System.out.print(bamboo[i][j]+" ");
        	}System.out.println();
        }

        int maxDate = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int tmp = checkDate(x, y);
                if(tmp > maxDate)
                    maxDate = tmp;
            }
        }

        System.out.println(maxDate);
    }

    // 사방향 확인.
    public static int checkDate(int x, int y){

        if(date[y][x] != 0) {
            return date[y][x];
        }

        int max = 1;

        for(int i=0; i<4; i++){

            int xx = x + xD[i];
            int yy = y + yD[i];

            if(xx < 0 || xx >= N || yy < 0 || yy >= N){
                continue;
            } else {
                if(bamboo[yy][xx] > bamboo[y][x]){
                    int tmp = checkDate(xx, yy) + 1;
                    if( tmp > max ) {
                        max = tmp;
                    }
                }
            }
        }
        return  date[y][x] = max;
    }
}