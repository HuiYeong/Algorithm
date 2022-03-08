package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class B14676_영우는사기꾼 {
	static int N, M, K, inDegree[], buildCnt[];
	static List<List<Integer>> list = new ArrayList<>();
	static List<Set<Integer>> remove = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		inDegree = new int[N+1]; // 진입 차수
		buildCnt = new int[N+1]; // 건물 개수
		for (int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
			remove.add(new HashSet<>());
		}
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(to); // 간선 저장
			inDegree[to]++; // 진입 차수 저장
		}
		
		for (int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int now = Integer.parseInt(st.nextToken());
			if ((order == 1 && inDegree[now] != 0) || // 건물 생성 시 지으려는 건물의 진입 차수가 0이 아니다 == 건물을 지을 수 없다
					(order == 2 && buildCnt[now] == 0)) { // 건물 파괴 시 건물 개수가 0이다 == 건물을 파괴할 수 없다
				System.out.println("Lier!");
				System.exit(0);
			}
			if (order == 1) { // 건물 생성
				buildCnt[now]++;
				for (int next : list.get(now)) { // 지을 수 있는 건물들
					if (!remove.get(next).contains(now)) { // 삭제된 건물이 아니라면
						remove.get(next).add(now); // 삭제 리스트에 추가
						if (inDegree[next] == 0) continue;
						inDegree[next]--; // 진입 차수 줄여주기
					}
				}
			} else if (order == 2) { // 건물 파괴
				buildCnt[now]--; 
				if (buildCnt[now] == 0) { // 건물을 파괴하여 건물이 아예 없다면
					for (int next : list.get(now)) {
						remove.get(next).clear(); // 삭제 리스트 초기화
						inDegree[next]++; // 다시 진입 차수 늘려줌
					}
				}
			}
		}
		System.out.println("King-God-Emperor");
	}
}
