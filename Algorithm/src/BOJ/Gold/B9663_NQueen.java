package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9663_NQueen {
	private static int[] col;
	private static int ans, N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new int[N+1];
		back(0);
		System.out.println(ans);
	}
	
	private static void back(int rowNo) {
		if (!Available(rowNo)) {
			return;
		}
		
		if (rowNo==N) {
			ans++;
			return;
		}
		
		for (int i=1;i<=N;i++) {
			col[rowNo+1] = i;
			back(rowNo+1);
		}
	}
	
	private static boolean Available(int rowNo) {
		for (int i=1;i<rowNo;i++) {
			if (col[rowNo]==col[i] || Math.abs(col[rowNo]-col[i])==rowNo-i) return false;
		}
		return true;
	}
}
