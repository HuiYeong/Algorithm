package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2961_도영이가만든맛있는음식 {
	private static int[][] taste;
	private static boolean[] isSelect;
	private static int N;
	private static long sour, bitter, min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		taste = new int[N][2];
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			taste[i][0] = Integer.parseInt(st.nextToken());
			taste[i][1] = Integer.parseInt(st.nextToken());
		}
		isSelect = new boolean[N];
		min = Integer.MAX_VALUE;
		subset(0);
		
		System.out.println(min);
	}
	
	private static void subset(int cnt) {
		if (cnt == N) {
			sour = 1;
			bitter = 0;
			cook();
			return;
		}
		
		isSelect[cnt] = true;
		subset(cnt+1);
		isSelect[cnt] = false;
		subset(cnt+1);
	}
	
	private static void cook() {
		for (int i=0;i<N;i++) {
			if (isSelect[i]) {
				sour *= taste[i][0];
				bitter+=taste[i][1];
			}
		}
		if (sour!=1 && bitter!=0) {
			long Answer = sour - bitter;
			min = Math.min(min, Math.abs(Answer));
		}
	}
}
