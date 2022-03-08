package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B6603_로또 {
	static int K;
	static int[] numbers, select;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			if (K==0) break;
			
			numbers = new int[K];
			select = new int[6];
			for (int i=0;i<K;i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			combination(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void combination(int cnt, int start) {
		if (cnt==6) {
			Arrays.sort(select);
			for (int sel : select) {
				sb.append(sel+" ");
			}
		
			sb.append("\n");
			return;
		}
			
		
		for (int i=start;i<K;i++) {
			select[cnt] = numbers[i];
			combination(cnt+1, i+1);
		}
	}
}
