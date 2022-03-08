package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11401_이항계수3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long P = 1000000007;
		long f[] = new long[N+1];
		f[0] = 1;
		f[1] = 1;
		for (int i=2;i<=N;i++) {
			f[i] = (f[i-1]*i)%P;
		}
		long A = f[N];
		long B = (f[K]*f[N-K])%P;
		long e = P-2;
		long b_pow_e = 1;
		while(e>0) {
			if (e%2==1) b_pow_e = (b_pow_e*B) %P;
			B = (B*B)%P;
			e/=2;
		}
		System.out.println(((A%P) * (b_pow_e%P))%P);
	}
}
