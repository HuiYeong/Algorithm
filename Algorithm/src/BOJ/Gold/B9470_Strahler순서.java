package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B9470_Strahler순서 {	
	static int TC, K, M, P, inDegree[];
	static Node nodeArr[];
	static List<List<Integer>> list;
	static Queue<int[]> queue = new LinkedList<>();
	static class Node {
		int num, MAX, cnt;

		public Node(int num, int MAX, int cnt) {
			super();
			this.num = num;
			this.MAX = MAX;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		while (TC-->0) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			inDegree = new int[M+1];
			nodeArr = new Node[M+1];
			list = new ArrayList<>();
			queue.clear();
			for (int i=0;i<=M;i++) {
				list.add(new ArrayList<>());
				nodeArr[i] = new Node(i, 0, 0);
			}
			for (int i=0;i<P;i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				list.get(v1).add(v2);
				inDegree[v2]++; // 진입 차수 저장
			}
			topology();
			sb.append(K+" "+nodeArr[M].MAX+"\n");
		}
		System.out.println(sb);
	}
	
	static void topology() {
		for (int i=1;i<=M;i++) {
			if (inDegree[i] == 0) queue.offer(new int[] {i, 1}); // 강의 근원인 노드의 순서는 1
		}
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if (now[0] == M) return; // 바다와 만나면 끝
			for (int next : list.get(now[0])) {
				if (nodeArr[next].MAX < now[1]) { // 나갈 강이 가진 Strahler의 순서보다 현재의 Strahler의 순서가 크면
					nodeArr[next].MAX = now[1]; // Strahler 순서 바꿔주고
					nodeArr[next].cnt = 1; // 개수는 1개
				} else if (nodeArr[next].MAX == now[1]) nodeArr[next].cnt++; // 만약 같다면 개수 ++
				
				
				if (--inDegree[next] == 0) { // 진입 차수가 0 == 들어갈 수 있는 강이면
					if (nodeArr[next].cnt > 1) nodeArr[next].MAX++; // Strahler 순서가 가장 큰 값이 2개 이상이면 큰 값 + 1
					queue.offer(new int[] {next, nodeArr[next].MAX}); // 들어갈 강의 번호와 순서 삽입
				}
			}
		}
	}
}
