package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2623_음악프로그램 {
	static int N, M, inDegree[], cnt;
	static List<List<Integer>> list = new ArrayList<>();
	static Queue<Integer> queue = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inDegree = new int[N+1];
		for (int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
		}
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken()); // 보조 PD가 담당하는 가수 수
			int from = Integer.parseInt(st.nextToken()); 
			for (int j=1;j<size;j++) {
				int to = Integer.parseInt(st.nextToken());
				list.get(from).add(to); // 간선 저장
				inDegree[to]++; // 진입 차수 저장
				from = to; // 현재 뒷 순서가 다음의 앞 순서
			}
		}
		if (topology()) System.out.println(sb);
		else System.out.println(0);
	}
	
	static boolean topology() {
		for (int i=1;i<=N;i++) {
			if (inDegree[i] == 0) { // 진입 차수가 0이면 진입 가능
				queue.offer(i);
				sb.append(i+"\n"); // 순서 저장
				cnt++;
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : list.get(now)) { // 이동할 수 있는 정점 중
				if (--inDegree[next] == 0) { // 진입 차수가 0이면 진입 가능
					queue.offer(next);
					sb.append(next+"\n");
					// 모든 가수들의 순서를 정하면 true 리턴
					if (++cnt == N) return true;
				}
			}
		}
		// 순서를 정하는 것이 불가능하면 false
		return false;
	}
}
