package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1992_쿼드트리 {
	private static int[][] map;
	private static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i=0;i<N;i++) {
			String split = br.readLine();
			for (int j=0;j<N;j++) {
				map[i][j] = split.charAt(j)-'0';
			}
		}
		cut(0,0,N);
		System.out.println(sb);
	}
	
	private static void cut(int row, int col, int len) {
		boolean trans = false;
		int temp = map[row][col];
		stop:for (int i=row;i<row+len;i++) {
			for (int j=col;j<col+len;j++) {
				if (temp != map[i][j]) {
					trans = true;
					break stop;
				}
			}
		}
		if (trans) {
			sb.append("(");
			int nextLen = len/2;
			cut(row, col, nextLen);
			cut(row, col+nextLen, nextLen);
			cut(row+nextLen, col, nextLen);
			cut(row+nextLen, col+nextLen, nextLen);
			sb.append(")");
		} else {
			sb.append(temp);
			return;
		}
	}
}
