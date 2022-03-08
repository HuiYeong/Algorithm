package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1780_종이의개수 {
	private static int[][] paper;
	private static int zero, one, minus, N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0, 0, N);
		sb.append(minus+"\n"+zero+"\n"+one);
		System.out.println(sb);
	}
	
	private static void divide(int r, int c, int size) {
		boolean flag = true;
		int e = paper[r][c];
		outer:for (int i=r;i<r+size;i++) {
			for (int j=c;j<c+size;j++) {
				if (paper[i][j]!=e) {
					flag = false;
					break outer;
				}
			}
		}
		
		if (!flag) {
			for (int i=0;i<3;i++) {
				divide(r, c, size/3);
				divide(r, c+size/3, size/3);
				divide(r, c+(size/3*2), size/3);
				r += size/3;
			}
		}
		else {
			if (e==0) zero++;
			else if (e==1) one++;
			else minus++;
			return;
		}
	}
}
