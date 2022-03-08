package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B13305_주유소 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] city = new long[N];
		long[] dis = new long[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N-1;i++) {
			dis[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			city[i] = Long.parseLong(st.nextToken());
		}
		// 입력 완료
		long ans = 0;
		for (int i=0;i<N-1;i++) {
			if (city[i]>=city[i+1]) ans+=dis[i]*city[i];
			else if (city[i]<city[i+1]) {
				long temp = 0, value = 0;
				value = city[i];
				while(true) {
					temp+=dis[i++];
					if (i+1>N-1 || value>city[i]) break;
				}
				i--;
				ans+=temp*value;
			}
		}
		System.out.println(ans);
	}
}
