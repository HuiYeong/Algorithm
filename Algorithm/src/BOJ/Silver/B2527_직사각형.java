package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2527_직사각형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<4;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int q1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());
			
			sb.append(check(x1, y1, p1, q1, x2, y2, p2, q2) + "\n");
		}
		System.out.println(sb);
	}
	
	private static String check(int x1, int y1, int p1, int q1, int x2, int y2, int p2, int q2) {
        if ((p1 == x2 && q1 == y2) || (x1 == p2 && q1 == y2) || (p1 == x2 && y1 == q2) || (x1 == p2 && y1 == q2)) {
            return "c";
        } else if ((p1 == x2 && q1 != y2) || (x1 == p2 && q1 != y2) || (p1 != x2 && y1 == q2) || (x1 != p2 && y1 == q2)) {
            return "b";
        } else if (p1 < x2 || p2 < x1 || q1 < y2 || q2 < y1) {
            return "d";
        } else {
            return "a";
        }
    }
}
