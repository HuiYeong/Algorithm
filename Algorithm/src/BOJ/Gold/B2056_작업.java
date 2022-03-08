package BOJ.Gold;

import java.io.*;
import java.util.*;

public class B2056_작업 {
	static int N, sum;
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
			int cnt = Integer.parseInt(st.nextToken());
			for (int j=0;j<cnt;j++) {
				graph.get(Integer.parseInt(st.nextToken())).add(i);
				cntList[i]++;
			}
		}
		pq = new PriorityQueue<>();
		System.out.println(topology());
	}
	
	static int topology() {
		for (int i=1;i<=N;i++) {
			if (cntList[i]==0) pq.offer(new Building(time[i], i));
		}
		int maxTime = Integer.MIN_VALUE;
		while(!pq.isEmpty()) {
			int size = pq.size();
			maxTime = Integer.MIN_VALUE;
			for (int i=0;i<size;i++) {
				Building now = pq.poll();
				maxTime = Math.max(maxTime, now.time);
				for (int next : graph.get(now.num)) {
					if (--cntList[next]==0) pq.offer(new Building(time[next]+now.time, next));
				}
			}
		}
		return maxTime;
	}
}