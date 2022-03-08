package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9466_텀프로젝트 {
	static int T, N, ans, students[];
	static boolean visit[], finish[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int t=0;t<T;t++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			students = new int[N+1];
			visit = new boolean[N+1];
			finish = new boolean[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i=1;i<=N;i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}
			for (int i=1;i<=N;i++) {
				if (!visit[i]) dfs(i);
			}
			sb.append((N-ans)+"\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int now) {
		visit[now] = true;
		int next = students[now];
		
		if (visit[next]) {
			if (!finish[next]) {
				for (int i=next;i!=now;i=students[i]) ans++;
				ans++;
			}
		} else dfs(next);
		finish[now] = true;
	}
}
