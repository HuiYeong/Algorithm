package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4948_베르트랑공준 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if (N==0) break;
			
			boolean[] cnt = new boolean[250000]; // 2n을 구해야하므로 제한되어있는 123,456보다 2배값을 크기로 설정
			
			for (int i=2;i*i<cnt.length;i++) {
				for (int j=i*i;j<cnt.length;j+=i) {
					cnt[j] = true;
				}
			}
			int ans = 0;
			for (int i=N+1;i<=2*N;i++) {
				if (!cnt[i]) ans++;
			}
			
			sb.append(ans+"\n");
		}
		System.out.println(sb);
	}
}
