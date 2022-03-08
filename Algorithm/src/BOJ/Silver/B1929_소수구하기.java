package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1929_소수구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		boolean[] check = new boolean[1000001];
		check[1] = true;
		for (long i=2;i*2<check.length;i++) {
			for (long j=i*i;j<check.length;j+=i) {
				check[(int) j]=true;
			}
		}
		
		for (int i=M;i<=N;i++) {
			if (!check[i]) sb.append(i+"\n");
		}
		System.out.println(sb);
	}
}
