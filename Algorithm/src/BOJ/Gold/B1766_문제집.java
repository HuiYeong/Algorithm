package BOJ.Gold;

import java.io.*;
import java.util.*;

public class B1766_문제집 {
	static int N, M, cnt;
	static boolean[] visit;
	static int[] cntLink;
	static StringBuilder sb;
	static PriorityQueue<Integer> problems;
	static List<List<Integer>> list;
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
		}
		visit = new boolean[N+1];
		cntLink = new int[N+1];
		problems = new PriorityQueue<>();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			list.get(v1).add(v2);
			cntLink[v2]++; // 후행 정점에 대한 간선의 수 증가
		}
		topology();
	}
	
	static  void topology() {
		for (int i=1;i<=N;i++) {
			if (cntLink[i]==0) {
				problems.offer(i);
			}
		}
		
		while(!problems.isEmpty()) {
			int v = problems.poll();
			sb.append(v+" ");
			cnt++;
			if (cnt==N) {
				System.out.println(sb);
				System.exit(0);
			}
			
			for (int nextV : list.get(v)) {
				cntLink[nextV]--;
				if (cntLink[nextV]==0) problems.offer(nextV);
			}
		}
	}
}