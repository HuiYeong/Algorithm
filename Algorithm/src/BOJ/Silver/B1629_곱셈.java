package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1629_곱셈 {
	private static int C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		long ans = divide(A, B);
		System.out.println(ans%C);
	}
	
	private static long divide(int A, int B) {
		if (B==1) return A;
		
		long r = divide(A, B/2);
		if (B%2==0) return (r*r)%C;
		else return (((r*r)%C)*A)%C;
	}
}
