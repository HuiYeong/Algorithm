package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B12852_1로만들기2 {
	static int N, cnt, visit[], size, now, value;
	static Queue<Integer> queue, store;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visit = new int[1000001];
		queue = new LinkedList<>();
		store = new LinkedList<>();
		sb = new StringBuilder();
		queue.offer(1);
		if (N==1) {
			System.out.println(0+"\n"+1);
			System.exit(0);
		}
		while(!queue.isEmpty()) {
			size = queue.size();
			for (int s=0;s<size;s++) {
				now = queue.poll();
				if (now == N) {
					sb.append(cnt+"\n"+now+" ");
					while(true) {
						value = visit[now];
						if (value == 1) break;
						store.offer(value);
						now = value;
					}
					store.offer(1);
					while(!store.isEmpty()) {
						sb.append(store.poll()+" ");
					}
					System.out.println(sb);
					System.exit(0);
				}
				if (now*3 < 1000001 && now*3 <= N && visit[now*3] == 0) {
					queue.offer(now*3);
					visit[now*3] = now;
				} 
				if (now*2 < 1000001 && now*2 <= N && visit[now*2] == 0) {
					queue.offer(now*2);
					visit[now*2] = now;
				} 
				if (now+1 < 1000001 && now+1 <= N && visit[now+1] == 0) {
					queue.offer(now+1);
					visit[now+1] = now;
				}
			}
			cnt++;
		}
	}
}
