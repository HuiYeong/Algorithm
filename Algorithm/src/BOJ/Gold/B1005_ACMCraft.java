package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1005_ACMCraft {
	static int TC, N, K, search, buildCost[], buildMin[], inDegree[];
	static List<List<Integer>> list;
	static Queue<Integer> queue = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		TC = Integer.parseInt(br.readLine());
		while(TC-->0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			// 초기화 시작
			buildCost = new int[N+1];
			buildMin = new int[N+1];
			inDegree = new int[N+1];
			list = new ArrayList<>();
			queue.clear();
			// 초기화 끝
			st = new StringTokenizer(br.readLine());
			list.add(new ArrayList<>());
			for (int i=1;i<=N;i++) {
				buildCost[i] = Integer.parseInt(st.nextToken()); // 건설 비용 저장
				list.add(new ArrayList<>());
			}
			for (int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list.get(from).add(to); // 간선 저장
				inDegree[to]++; // 진입 차수 저장
			}
			search = Integer.parseInt(br.readLine()); // 승리하기 위해 건설해야 할 건물의 번호
			sb.append(topology()+"\n"); 
		}
		System.out.println(sb);
	}
	
	static int topology() {
		for (int i=1;i<=N;i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
				buildMin[i] = buildCost[i]; 
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if (now == search) return buildMin[now]; // 건설 완료하면 시간 리턴해주면 끝
			for (int next : list.get(now)) {
				// 최소 시간이지만 앞서 지을 건물이 모두 완성되어야 지을 수 있으므로 사실상 이동할 때 걸리는 시간의 최대
				buildMin[next] = Math.max(buildMin[next], buildMin[now]+buildCost[next]);
				if (--inDegree[next] == 0) queue.add(next);
			}
		}
		return -1;
	}
}
