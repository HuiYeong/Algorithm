package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B12761_돌다리 {
	static int A, B, N, M, cnt;
	static boolean[] visit;
	static Queue<Integer> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visit = new boolean[100001];
		queue = new LinkedList<>();
		queue.offer(N);
		visit[N] = true;
		System.out.println(bfs());
	}
	
	static int bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int s=0;s<size;s++) {
				int now = queue.poll();
				if (now==M) return cnt;
				add(now+1);
				add(now-1);
				add(now+A);
				add(now-A);
				add(now+B);
				add(now-B);
				add(now*A);
				add(now*B);
			}
			cnt++;
		}
		return 0;
	}
	
	static void add(int a) {
		if (a<0 || a>100000 || visit[a]) return;
		
		queue.offer(a);
		visit[a] = true;
	}
}
