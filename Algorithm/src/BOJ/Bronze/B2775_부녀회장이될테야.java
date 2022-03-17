package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 미리 층, 호수에 대한 사람 수를 다 구한 뒤 테스트 케이스를 돌린다.
 * 아래 층 T-1층 1호~N-1호까지의 사람 수는 같은 층인 T층 N-1호에 이미 저장되어 있기 때문에
 * 이미 저장된 N-1호의 사람 수 + 현재 나의 층까지의 사람 수인 T-1층의 N호 더해주면 끝
 */
public class B2775_부녀회장이될테야 {
	static int TC, arr[][] = new int[15][15], start, K, N; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int j=1;j<15;j++) {
			arr[0][j] = ++start;
			for (int i=1;i<15;i++) {
				arr[i][j] = arr[i-1][j]+arr[i][j-1];
			}
		}
		TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(TC-->0) {
			K = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			sb.append(arr[K][N]+"\n");
		}
		System.out.println(sb);
	}
}
