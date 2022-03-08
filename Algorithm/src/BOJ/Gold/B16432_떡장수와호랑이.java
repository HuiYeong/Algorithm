package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B16432_떡장수와호랑이 {
	static int N;
	static List<List<Integer>> ricecake;
	static int[] ans;
	static boolean[][] visited;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = new int[N];
		visited = new boolean[N][10];
		StringTokenizer st = null;
		ricecake = new ArrayList<>();
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			ricecake.add(new ArrayList<>());
			for (int j=0;j<M;j++) {
				ricecake.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		bfs(0, 0);
		System.out.println(-1);
	}
	
	static void bfs(int cnt, int select) {
		if (cnt==N) {
			for (int i=0;i<N;i++) {
				sb.append(ans[i]+"\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		for (int i=0;i<ricecake.get(cnt).size();i++) {
			int now = ricecake.get(cnt).get(i);
			if (now != select && !visited[cnt][i]) {
				visited[cnt][i] = true;
				ans[cnt] = now;
				bfs(cnt+1, now);
			}
		}
		return;
	}
}
