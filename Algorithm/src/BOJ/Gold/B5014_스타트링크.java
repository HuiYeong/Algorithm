package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5014_스타트링크 {
	static int F, S, G, U, D;
	static int[] visit;
	static Queue<Integer> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		visit = new int[F+1];
		queue = new LinkedList<>();
		queue.offer(S);
		Arrays.fill(visit, -1);
		visit[S] = 0;
		System.out.println(bfs());
	}
	
	static String bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int s=0;s<size;s++) {
				int now = queue.poll();
				if (now==G) return(visit[now]+"");
				bfsInput(now, now+U);
				bfsInput(now, now-D);
			}
		}
		return "use the stairs";
	}
	
	static void bfsInput(int now, int next) {
		if (next>0 && next<=F && visit[next]==-1) {
			queue.offer(next);
			visit[next] = visit[now]+1;
		}
	}
}
