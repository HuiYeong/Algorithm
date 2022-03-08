package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1753_최단경로 {
	static int V, E, start;
	static int[] dis;
	static LinkNode[] graph;
	static class LinkNode implements Comparable<LinkNode>{
		int to, weight;
		LinkNode link;
		public LinkNode(int to, int weight, LinkNode link) {
			super();
			this.to = to;
			this.weight = weight;
			this.link = link;
		}
		@Override
		public int compareTo(LinkNode o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수
		start = Integer.parseInt(br.readLine()); // 시작 위치
		
		graph = new LinkNode[V+1];
		
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from] = new LinkNode(to, weight, graph[from]);
		}
		
		dijkstraPQ(start);
		for (int i=1;i<=V;i++) {
			if (dis[i]==987654321) sb.append("INF\n");
			else sb.append(dis[i]+"\n");
		}
		
		
		System.out.println(sb);
	}
	
	static void dijkstraPQ(int start) {
		boolean[] visited = new boolean[V+1];
		dis = new int[V+1];
		int INF = 987654321;
		Arrays.fill(dis, INF);
		
		PriorityQueue<LinkNode> pq = new PriorityQueue<>();
		
		// 출발지 선정
		dis[start] = 0;
		pq.offer(new LinkNode(start, 0, null));
		
		while(!pq.isEmpty()) {
			LinkNode head = pq.poll();
			if (visited[head.to]) continue;
			
			visited[head.to] = true;
			
			LinkNode child = graph[head.to];
			while(child != null) {
				if (!visited[child.to] && dis[child.to]>dis[head.to]+child.weight) {
					dis[child.to] = dis[head.to] + child.weight;
					pq.add(new LinkNode(child.to, dis[child.to], head));
				}
				child = child.link;
			}
 		}
	}
}