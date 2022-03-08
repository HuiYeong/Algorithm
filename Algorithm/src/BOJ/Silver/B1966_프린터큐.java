package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1966_프린터큐 {
	public static class printer{
		int i, priority;

		public printer(int i, int priority) {
			super();
			this.i = i;
			this.priority = priority;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Queue<printer> queue = new LinkedList<>();
			List<Integer> priority = new ArrayList<>();
 			st = new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) {
				int pri = Integer.parseInt(st.nextToken());
				queue.offer(new printer(i, pri));
				priority.add(pri);
			}
			Collections.sort(priority, (o1, o2)-> {
				return o2-o1;
			});
			
			int cnt = 1, i=0;
			while(!queue.isEmpty()) {
				if (queue.peek().i==M && priority.get(i)<=queue.peek().priority) break;
				if (priority.get(i)<=queue.peek().priority) {
					i++;
					cnt++;
					queue.poll();
				}
				else queue.add(queue.poll());
			}
			sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}
}
