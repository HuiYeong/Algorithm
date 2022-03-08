package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1697_숨바꼭질 {
	private static int N, K, ans;
	private static Queue<move> bfs;
	private static boolean[] check;
	public static class move {
		int n, cnt;

		public move(int n, int cnt) {
			super();
			this.n = n;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		check = new boolean[100001];
		bfs = new LinkedList<>();
		ans = 0;
		bfsL();
		System.out.println(ans);
	}
	
	private static void bfsL() {
		bfs.add(new move(N, 0));
		
		while(!bfs.isEmpty()) {
			move now = bfs.poll();
			if (now.n > 100000 || now.n<0 || check[now.n]) continue;
			if (now.n==K) {
				ans = now.cnt;
				break;
			}
			bfs.add(new move(now.n-1, now.cnt+1));
			bfs.add(new move(now.n+1, now.cnt+1));
			bfs.add(new move(now.n+now.n, now.cnt+1));
			check[now.n] = true;
		}
	}
}
