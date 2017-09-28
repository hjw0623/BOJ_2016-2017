package kakao.firstblind.solution7;

import java.util.StringTokenizer;

public class Solution {

	public static int solution(String[] lines) {
		int answer = 0;
		int result[] = new int [lines.length];
		int end[] = new int[lines.length];
		int[][][][] timetable = new int[24][60][60][1000];
		for (int i = 0; i < lines.length; i++) {
			StringTokenizer st = new StringTokenizer(lines[i]);
			String dump = st.nextToken();
			String time = st.nextToken();
//			System.out.println("time:" + time);
			int hour = Integer.parseInt(time.substring(0, 2));
			int min = Integer.parseInt(time.substring(3, 5));
			int sec = Integer.parseInt(time.substring(6, 8));
			int parsec = Integer.parseInt(time.substring(9, 12));
			timetable[hour][min][sec][parsec] += 1;

			int compare = hour * 60 * 60 * 1000 + min * 60 * 1000 + sec * 1000 + parsec;
//			System.out.println(compare);
//			System.out.println(hour + " " + min + " " + sec + " " + parsec);
			String during = st.nextToken();
			during = during.substring(0, during.length() - 1);
			int duringInt = (int) Double.parseDouble(during) * 1000;
//			System.out.println(duringInt);

			int count = compare - duringInt;
			result[i] = count;
			end[i] = compare;
			
//			System.out.println("count: " + count);
			int khour = (int) count / 3600000;
//			System.out.println("hour " + khour);
			int kmin = (int) (count - khour * 3600000) / 60000;
//			System.out.println(kmin);

			int ksec = (int) (count - khour * 3600000 - kmin * 60000) / 1000;
//			System.out.println(ksec);

			for (int k = count; k < compare; k++) {
				int kparsec = (k - khour * 3600000 - kmin * 60000) % 1000;
				timetable[khour][kmin][ksec][kparsec] += 1;
			}
			
			

		}
		int tmpMax = 0;
		int retMax = 0;
		
		for (int i = 0; i < timetable.length; i++) {
			for (int j = 0; j < timetable[i].length; j++) {
				for (int k = 0; k < timetable[i][j].length; k++) {
					tmpMax =0;
					for(int l = 0; l<timetable[i][j][k].length; l++){
						tmpMax += timetable[i][j][k][l];
					}
					tmpMax = tmpMax/1000;
					retMax = Math.max(tmpMax, retMax);
					if(retMax ==0)retMax =1;
				}
			}
		}
		
//		for(int k = 0; )
		return answer=retMax;
	}

	public static void main(String[] args) {

		String s1 = "2016-09-15 01:00:04.002 2.0s";
		String s2 = "2016-09-15 01:00:07.000 2s";
		String strs[] = { s1, s2 };

		int ret = solution(strs);
		System.out.println(ret);
	}

}
