package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2609_최대공약수와최소공배수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cnt = 2, max = 1, min = 1;
		while(true) {
			if (cnt>Math.max(N, M)) break;
			if (N%cnt==0 && M%cnt==0) {
				max *= cnt;
				N/=cnt; M/=cnt;
				cnt = 1;
			}
			cnt++;
		}
		min = max*N*M;
		sb.append(max+"\n"+min);
		System.out.println(sb);
	}
}
