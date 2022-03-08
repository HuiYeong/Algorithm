package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14696_딱지놀이 {
	private static int[][] card;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int round = Integer.parseInt(br.readLine());
		for (int r=0;r<round;r++) { // 라운드
			int Answer = 0; // 1이면 A승, 2면 B승, 0이면 무승부
			card = new int[2][4];
			boolean isA = true;
			for (int i=0;i<2;i++) { // A랑 B 카드 정보 입력
				StringTokenizer st = new StringTokenizer(br.readLine());
				int N = Integer.parseInt(st.nextToken()); // 카드 개수
				for (int n=0;n<N;n++) {
					if (isA) {
						card[0][Integer.parseInt(st.nextToken())-1]++;
					}
					else card[1][Integer.parseInt(st.nextToken())-1]++;
				}
				isA = false;
			}
			
			if (card[0][3] == card[1][3]) {
				if (card[0][2] == card[1][2]) {
					if (card[0][1] == card[1][1]) {
						if (card[0][0] == card[1][0]) Answer = 0;
						else Answer = card[0][0]>card[1][0]?1:2;
					} else Answer = card[0][1]>card[1][1]?1:2; 
				} else Answer = card[0][2]>card[1][2]?1:2;
			} else Answer = card[0][3]>card[1][3]?1:2;
			
			if (Answer==0) sb.append("D\n");
			else if (Answer==1) sb.append("A\n");
			else sb.append("B\n");
		}
		System.out.println(sb);
	}
}
