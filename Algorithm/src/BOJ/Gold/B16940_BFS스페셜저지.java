package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class B16940_BFS스페셜저지 {
	static int N, idx = 1, ans[];
	static boolean visit[];
	static Set<Integer> set = new HashSet<>();
	static Queue<Integer> queue = new LinkedList<>();
	static List<List<Integer>> list = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		visit = new boolean[N+1];
		ans = new int[N];
		for (int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
		}
		for (int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			// 양방향으로 간선 저장
			list.get(from).add(to);
			list.get(to).add(from);
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			ans[i] = Integer.parseInt(st.nextToken());
		}
		// 시작은 무조건 1
		if (ans[0] != 1) {
			System.out.println(0);
			System.exit(0);
		}
		queue.offer(1);
		visit[1] = true;
		System.out.println(bfs());
	}
	
	static int bfs() {
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : list.get(now)) {
				// 연결 되어 있는 정점 중 방문하지 않은 곳 방문
				if (!visit[next]) {
					visit[next] = true;
					set.add(next);
				}
			}
			
			// 해당 레벨에서 추가된 정점들 사이즈
			int size = set.size();
			
			// 지금까지 검사 된 인덱스에서 추가된 인덱스까지 값이 있는지 검사
			for (int i=idx;i<idx+size;i++) {
				// 값이 있다면 큐에 삽입
				if (set.contains(ans[i])) queue.offer(ans[i]);
				// 없다면 방문 순서가 올바르지 않은 방문 순서이므로 0 리턴
				else return 0;
			}
			
			// 검사 완료 후 인덱스 추가
			idx += size;
			set.clear();
		}
		// 만약 큐가 비었다면 올바른 방문 순서이므로 1을 리턴
		return 1;
	}
}
