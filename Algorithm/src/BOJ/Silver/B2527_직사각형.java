package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <수학, 기하학 문제 >
 * 
 * 겹칠 때의 조건이 가장 많으니 점일 떄, 분리 되었을 때, 선분일 때로 나눠서 조건처리 후 겹칠 때는 마지막에 else로 처리
 * 점 - 꼭짓점이 같다.
 * 분리 - 끝 점끼리 겹치지 않는다.
 * 선분 - 한 면이 맞닿는다.
 */
public class B2527_직사각형 {
	static int x1, y1, p1, q1, x2, y2, p2, q2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<4;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			p1 = Integer.parseInt(st.nextToken());
			q1 = Integer.parseInt(st.nextToken());

			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			p2 = Integer.parseInt(st.nextToken());
			q2 = Integer.parseInt(st.nextToken());
			
			sb.append(check() + "\n");
		}
		System.out.println(sb);
	}
	
	private static String check() {
		// 점일 때
		if((p1==x2 && q1==y2) || (x1==p2 && q1==y2) || (x1==p2 && y1==q2) || (p1==x2 && y1==q2)) return "c";
		// 완전 분리 되었을 때
		else if(q1<y2 || p1<x2 || q2<y1 || p2<x1) return "d";
		// 선분일 때
		else if(p1==x2 || q1==y2 || x1==p2 || y1==q2) return "b";
		// 겹칠 때 (경우의 수가 많으니 아예 조건 빼주기
		else return "a";
    }
}
