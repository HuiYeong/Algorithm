package BOJ.Gold;

import java.io.*;
import java.util.*;

public class B2252_줄세우기 {
	static int N, M, cnt;
	static int[] cntList;
	static Queue<Integer> queue;
	static List<List<Integer>> list;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cntList = new int[N+1];
		list = new ArrayList<>();
		queue = new LinkedList<>();
		for (int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			list.get(v1).add(v2);
			cntList[v2]++;
		}
		topology();
		System.out.println(sb);
	}
	
	static void topology() {
		for (int i=1;i<=N;i++) {
			if (cntList[i]==0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int v = queue.poll();
			sb.append(v+" ");
			for (int next : list.get(v)) {
				if (--cntList[next] == 0) queue.offer(next);
			}
		}
	}
}