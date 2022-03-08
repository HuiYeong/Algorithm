package BOJ.Gold;

import java.io.*;
import java.util.*;

public class B1516_게임개발 {
	static int N;
	static List<List<Integer>> graph;
	static int[] cntList, time, ans;
	static StringBuilder sb;
	static PriorityQueue<Building> pq;
	static class Building implements Comparable<Building>{
		int time, num;

		public Building(int time, int num) {
			super();
			this.time = time;
			this.num = num;
		}

		@Override
		public int compareTo(Building o) {
			return Integer.compare(this.time, o.time);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		cntList = new int[N+1];
		time = new int[N+1];
		ans = new int[N+1];
		for (int i=0;i<=N;i++) {
			graph.add(new  ArrayList<>());
		}
		StringTokenizer st = null;
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				int value = Integer.parseInt(st.nextToken());
				if (value==-1) break;
				graph.get(value).add(i);
				cntList[i]++;
			}
		}
		pq = new PriorityQueue<>();
		topology();
		for (int i=1;i<=N;i++) {
			sb.append(ans[i]+"\n");
		}
		System.out.println(sb);
	}
	
	static void topology() {
		for (int i=1;i<=N;i++) {
			if (cntList[i]==0) pq.offer(new Building(time[i], i));
		}
		
		while(!pq.isEmpty()) {
			Building now = pq.poll();
			ans[now.num] = now.time;
			for (int next : graph.get(now.num)) {
				if (--cntList[next]==0) pq.offer(new Building(time[next]+now.time, next));
			}
		}
	}
}