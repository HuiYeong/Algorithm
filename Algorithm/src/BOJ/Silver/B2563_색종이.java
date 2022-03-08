package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2563_색종이 {
	private static int[][] paper;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		paper = new int[100][100];
		int N = Integer.parseInt(in.readLine());
		int Answer = 0;
		for (int n=0;n<N;n++) {
			StringTokenizer st = new StringTokenizer(in.readLine()," ");
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			
			for (int i=100-Y-1;i>100-Y-11;i--) {
				for (int j=X;j<X+10;j++) {
					paper[i][j] = 1;
				}
			}
		}
		for (int i=0;i<100;i++) {
			for (int j=0;j<100;j++) {
				if (paper[i][j] == 1) Answer++;
			}
		}
		System.out.println(Answer);
	}
}
