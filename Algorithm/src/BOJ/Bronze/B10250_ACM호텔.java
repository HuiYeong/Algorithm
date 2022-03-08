package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10250_ACM호텔 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int Tc = Integer.parseInt(in.readLine());
		for (int tc=1;tc<=Tc;tc++) {
			st = new StringTokenizer(in.readLine()," ");
			int H = Integer.parseInt(st.nextToken()); // 층수
			int W = Integer.parseInt(st.nextToken()); // 방수
			int N = Integer.parseInt(st.nextToken()); // 몇 번째 손님?
			String ho = null;
			int dong = N%H;
			if (dong == 0) {
				dong = H;
				ho = (N/H)+"";
			}
			else ho = (N/H+1)+"";
			sb.append(dong);
			if (ho.length()==1) sb.append("0"+ho+"\n");
			else sb.append(ho+"\n");
		}
		System.out.println(sb);
	}
}
