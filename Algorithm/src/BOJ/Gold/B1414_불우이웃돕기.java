package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * < 최소 스패닝 트리 문제 >
 * 
 * 기본 스패닝 트리 문제로 입력 시 들어오는 간선의 값을 처리하는 부분만 조금 다르다.
 * 간선의 총 합을 구한 뒤 연결하는 간선 값을 빼주면 끝
 */
public class B1414_불우이웃돕기 {
	static int root[], totalLan, weight, N, cnt, connectLan;
	static List<Edge> edgeList = new ArrayList<>();
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		root = new int[N+1];
		for (int i=1;i<=N;i++) {
			String input = br.readLine();
			for (int j=1;j<=N;j++) {
				char now = input.charAt(j-1);
				if (now>='a' && now<='z') {
					weight = (now-'a') + 1;
					totalLan += weight;
					edgeList.add(new Edge(i, j, weight));
				}
				else if (now>='A' && now<='Z') {
					weight = (now-'A') + 27;
					totalLan += weight;
					edgeList.add(new Edge(i, j, weight));
				}
			}
		}
		
		makeSet();
		Collections.sort(edgeList); // 간선 오름차순 정렬
		
		for (Edge edge : edgeList) {	
			if (union(edge.from, edge.to)) {
				totalLan -= edge.weight;
				cnt++; // 연결 개수 확인
			}
		}
		
		System.out.println(cnt==N-1?totalLan:-1); // 모두 연결이 되었다면 기부할 수 있는 랜선 값 출력, 아니라면 -1 출력
	}
	
	static void makeSet() {
		for (int i=1;i<=N;i++) {
			root[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (root[x] < 0) return x;
		return root[x] = findSet(root[x]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		root[bRoot] = aRoot;
		return true;
	}
}
