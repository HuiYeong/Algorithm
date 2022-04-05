package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * < 그리디 문제 >
 * 
 * 손상을 입은 팀과 여분의 카약이 있는 팀을 배열을 통해 구분한다.
 * 팀을 쭉 탐색하며 손상이 있는 팀일 때
 * 먼저 자신의 팀에 여분의 카약이 있는지 확인한다.
 * 없다면 자기 앞에 있는 팀에게 여분의 카약이 있는지 확인한다.
 * 없다면 자기 뒤에 있는 팀에게 여분의 카약이 있는지 확인한다.
 * 뒤에 있는 팀도 손상된 팀이라면 자신의 카약을 본인의 팀에게 써야할 수 있으므로 다음 팀의 손상 여부도 확인한다.
 */
public class B2891_카약과강풍 {
	static int N, S, R;
	static boolean damage[], more[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		damage = new boolean[N+1];
		more = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<S;i++) {
			int value = Integer.parseInt(st.nextToken());
			damage[value] = true;
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<R;i++) {
			int value = Integer.parseInt(st.nextToken());
			more[value] = true;
		}
		int ans = 0;
		for (int i=1;i<=N;i++) {
			if (!damage[i]) continue;
			if (more[i]) {
				more[i] = false;
				continue;
			} 
			if (more[i-1]) {
				more[i-1] = false;
				continue;
			}
			if (i!=N && !damage[i+1] && more[i+1]) {
				more[i+1] = false;
				continue;
			}
			ans++;
		}
		System.out.println(ans);
	}
}
