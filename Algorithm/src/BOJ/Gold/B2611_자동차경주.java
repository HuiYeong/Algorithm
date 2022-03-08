package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2611_자동차경주 {
	static int N, M, from, to, weight, inDegree[];
	static Node nodeArr[]; // 노드마다 점수가 가장 큰 값과 경로를 저장하기 위한 배열
	static List<List<int[]>> list = new ArrayList<>(); // 도로 정보를 저장하는 리스트
	static Queue<Integer> queue = new LinkedList<>(); // 위상 정렬을 돌리기 위한 큐
	static class Node {
		int sum;
		String path; // 경로 저장할 변수
		public Node(int sum, String path) {
			super();
			this.sum = sum;
			this.path = path;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		inDegree = new int[N+1];
		nodeArr = new Node[N+1];
		for (int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
			nodeArr[i] = new Node(0, "");
		}
		M = Integer.parseInt(br.readLine());
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			list.get(from).add(new int[] {to, weight});
			inDegree[to]++;
		}
		topology();
		System.out.println(nodeArr[1].sum+"\n"+nodeArr[1].path.substring(1, nodeArr[1].path.length()));
	}
	
	static void topology() {
		queue.offer(1); // 1번 지점에서 출발
		while(!queue.isEmpty()) {
			int now = queue.poll();
			Node nowNode = nodeArr[now];
			nowNode.path = nowNode.path + " " +now; // 경로 저장
			if (nodeArr[1].sum != 0 && now == 1) return;
			
			for (int[] next : list.get(now)) {
				// 해당 지점의 경로 점수보다 현재까지의 경로 점수 + 다음 경로 점수가 더 클 때 
				if (nodeArr[next[0]].sum < nowNode.sum + next[1]) {
					nodeArr[next[0]].sum = nowNode.sum + next[1]; // 점수 변경
					nodeArr[next[0]].path = nowNode.path; // 경로 변경
				}
				if (--inDegree[next[0]] == 0) queue.offer(next[0]);
			} 
		}
	}
}
