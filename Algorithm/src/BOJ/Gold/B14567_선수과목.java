package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14567_선수과목 {
	static int N, M, inDegree[], size, cnt, ans[];
	static List<List<Integer>> list = new ArrayList<>();
	static Queue<Integer> queue = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
		}
		inDegree = new int[N+1];
		ans = new int[N+1];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			list.get(v1).add(v2); // 간선 저장
			inDegree[v2]++; // 진입 차수 저장
		}
		topology();
		for (int i=1;i<=N;i++) {
			sb.append(ans[i]+" ");
		}
		System.out.println(sb);
	}
	
	static void topology() {
		for (int i=1;i<=N;i++) {
			if (inDegree[i] == 0) queue.offer(i); // 집입 차수가 0이다 == 바로 이수할 수 있다
		}
		cnt = 1; // 학기를 카운트 할 변수
		while(!queue.isEmpty()) {
			size = queue.size();
			while(size-->0) {
				int v = queue.poll(); // 선택된 정점 뽑기
				ans[v] = cnt; // 이수 학기 저장
				for (int next : list.get(v)) { // 내가 이동할 수 있는 간선 리스트
                	// 부속된 간선 삭제 == 내가 이동할 간선의 진입 차수 -1 (내가 빠졌으니)
					if (--inDegree[next] == 0) queue.offer(next);
				}
			}
			cnt++; // 한 번 돌 때마다 한 학기씩 증가
		}
	}
}