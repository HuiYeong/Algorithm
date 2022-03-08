package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1018_체스판다시칠하기 {
	private static int N, M;
	private static char[][] chess;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chess = new char[N][M];
		
		// 입력 받기
		for (int i=0;i<N;i++) {
			String line = in.readLine();
			for (int j=0;j<M;j++) {
				chess[i][j] = line.charAt(j);
			}
		}
		
		int min = Integer.MAX_VALUE; 
		char[] compare = {'B', 'W'};
		int BW = 0;
		for (int k=0;k<2;k++) {
			int startI = 0, startJ = 0; // 시작 위치
			BW = k==0?0:1;
			do {
				int cnt = 0;
				for (int i=startI;i<8+startI;i++) {
					for (int j=startJ;j<8+startJ;j++) {
						if (chess[i][j]!=compare[BW]) cnt++;
						BW = BW == 1?0:1;
					}
					BW = BW == 1?0:1;
				}
				
				min = Math.min(min, cnt);
				startJ++;
				if (startJ+8>M) {
					startJ=0; startI++;
				}
			} while(startI+8<=N);
		}
		
		System.out.println(min);
	}
}
