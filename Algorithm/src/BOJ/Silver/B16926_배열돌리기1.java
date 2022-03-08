package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16926_배열돌리기1 {
	private static int[][] map;
	private static int N, M,S;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		S = Math.min(N, M) / 2;
		
		map = new int[N][M];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine()," ");
			for (int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(R%((N+M-2)*2));
		
		for (int i=0;i<R%((N+M-2)*2);i++) {
			for (int s = 0; s<S;s++) {
				rotation(0, 0);
			}
			S = ((M-1)+(N-1)-2)*2;
		}
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) sb.append(map[i][j]+" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void rotation(int startI, int startJ) {
		int temp = map[startI][startJ];
		for (int j=startJ;j<M-startJ-1;j++) map[startI][j] = map[startI][j+1];
		for (int i=startI;i<N-startI-1;i++) map[i][M-startJ-1] = map[i+1][M-startJ-1];
		for (int j=M-startJ-1;j>startJ;j--) map[N-startI-1][j] = map[N-startI-1][j-1];
		for (int i=N-startI-1;i>startI;i--) map[i][startJ] = map[i-1][startJ];
		map[startI+1][startJ] = temp;
		startI++; startJ++;
	}
}
