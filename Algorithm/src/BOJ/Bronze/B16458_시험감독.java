package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16458_시험감독 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] examC = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			examC[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long cnt = 0;
		for (int i=0;i<N;i++) {
			
			cnt++;
			examC[i]-=B;
			if (examC[i] > 0) {
				if (examC[i] % C != 0) cnt++;
				cnt += examC[i] / C;
			}
		}
		System.out.println(cnt);
	}
}
