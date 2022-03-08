package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2630_색종이만들기 {
	static int N, blue, white;
	static int[][] arr;
	static boolean flag;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력완료
		divide(0, 0, N);
		System.out.println(white+"\n"+blue);
	}
	
	static void divide(int r, int c, int length) {
		flag = false;
		int temp = arr[r][c];
		
		stop:for (int i=r;i<r+length;i++) {
			for (int j=c;j<c+length;j++) {
				if (arr[i][j]!=temp) {
					flag = true;
					break stop;
				}
			}
		}
		if (!flag) {
			if (temp==1) blue++;
			else white++;
			return;
		}
		if (flag) {
			int size = length/2;
			divide(r, c, size);
			divide(r, c+size, size);
			divide(r+size, c, size);
			divide(r+size, c+size, size);
		}
	}
}
