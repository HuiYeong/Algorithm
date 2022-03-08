package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759_암호만들기 {
	private static int[] numbers;
	private static boolean[] isSelect;
	private static char[] alpha;
	private static int L, C;
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alpha = new char[C];
		numbers = new int[L];
		isSelect = new boolean[C];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<C;i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alpha);
		permutation(0);
		System.out.println(sb);
	}
	
	private static void permutation(int cnt) {
		if (cnt==L) {
			boolean flag = false;
			int count = 0;
			for (int k=0;k<L;k++) {
				if (alpha[numbers[k]]=='a' || alpha[numbers[k]]=='e' || alpha[numbers[k]]=='i'
						|| alpha[numbers[k]]=='o' || alpha[numbers[k]]=='u') flag = true;
				else count++;
			}
			if (count<2) flag = false;
			if (!flag) return;
			for (int j=0;j<L;j++) {
				sb.append(alpha[numbers[j]]);
			}
			sb.append("\n");
			
			return;
		}
		
		for (int j=0;j<C;j++) {
			for (int k=0;k<cnt;k++) {
				if (k+1<L && numbers[k]>j) isSelect[j] = true;
			}
			
			if (isSelect[j]) {
				isSelect[j] = false;
				continue;
			}
			numbers[cnt] = j;
			isSelect[j] = true;
			permutation(cnt+1);
			isSelect[j] = false;
		}
	}
}
