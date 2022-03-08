package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17406_배열돌리기4 {
	private static int[][] map;
	private static int N, M, K;
	private static int r, c, s, min;
	private static int[] choosed;
	private static boolean[] isSelected;
	private static int[][] temp;
	private static int[] per;
	private static int[][] map2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		map2 = new int[N][M];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine()," ");
			for (int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map2[i][j] = map[i][j];
			}
		} 
		
		min = Integer.MAX_VALUE;
		choosed = new int[K];
		per = new int[K+1];
		isSelected = new boolean[K+1];
		temp = new int[K+1][3];
		for (int i=1;i<=K;i++) {
			st = new StringTokenizer(in.readLine()," ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			temp[i][0] = r; temp[i][1] = c; temp[i][2] = s;
		}
		
		for (int i=1;i<=K;i++) per[i] = i;
		
		permutation(0);
	
		System.out.println(min);
	}
	
	private static void permutation(int cnt) {
		if (cnt == K) {
			for (int i=0;i<K;i++) {
				int len = (2*temp[choosed[i]][2])+1;
				int ro = len/2;
				rotation(temp[choosed[i]][0], temp[choosed[i]][1], temp[choosed[i]][2], len, ro);
			}
			
			
			for (int i=0;i<N;i++) {
				int sum = 0;
				for (int j=0;j<M;j++) {
					sum+= map[i][j];
				}
				min = Math.min(min,  sum);
			}
			
			for (int i=0;i<N;i++) {
				for (int j=0;j<M;j++) {
					map[i][j] = map2[i][j];
				}
			}
			return;
		}
		
		for (int i=1;i<=K;i++) {
			if (isSelected[i]) continue;
			
			choosed[cnt] = per[i];
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
	private static void rotation(int r, int c, int s, int len, int ro) {
		int startI = r-s-1, startJ = c+s-1;
		for (int k = 0; k<ro;k++) {
			int L = (c+s-1)-len+(k+1) , R = (r-s-1)+len-(k+1);
			int temp = map[startI][startJ];
			for (int j=startJ;j>L;j--) map[startI][j] = map[startI][j-1];
			for (int i=startI;i<R;i++) map[i][L] = map[i+1][L];
			for (int j=L;j<startJ;j++) map[R][j] = map[R][j+1];
			for (int i=R;i>startI;i--) map[i][startJ] = map[i-1][startJ];
			map[startI+1][startJ] = temp;
			startI++; startJ--;
		}
	}
}
