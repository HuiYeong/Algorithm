package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2637_장난감조립 {
	static int N, M, X, Y, K, inDegree[], arr[][] = new int[101][101]; // arr : 부품 개수 저장
	static boolean base[]; // 기본 부품 확인
	static List<List<Integer>> list = new ArrayList<>(); // 간선 저장
	static List<List<Integer>> partList = new ArrayList<>(); // 완제품 X를 만드는데 필요한 부품 저장
	static Queue<Integer> queue = new LinkedList<>(); // 위상 정렬을 돌리기 위한 큐
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 노드 개수
		M = Integer.parseInt(br.readLine()); // 간선 개수
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		inDegree = new int[N+1];
		base = new boolean[N+1];
		for (int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
			partList.add(new ArrayList<>());
		}
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			inDegree[X]++;
			list.get(Y).add(X);
			partList.get(X).add(Y); // 완제품 또는 중간부품 X를 만드는데 필요한 부품은 Y
			arr[X][Y] += K; // 완제품 또는 중간부품 X를 만드는데 필요한 부품의 개수는 K
		}
		topology();

		for (int i=1;i<=N;i++) {
			if (base[i]) sb.append(i+" "+arr[N][i]+"\n"); // 완제품 N의 기본 부품 출력
		}
		
		System.out.println(sb);
	}
	
	static void topology() {
		for (int i=1;i<=N;i++) {
			if (inDegree[i] == 0) { // 진입 차수가 0 == 기본 부품
				base[i] = true; // 기본 부품 처리
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if (now == N) return;
			for (int next : list.get(now)) {
				if (--inDegree[next] == 0) {
					queue.offer(next);
					
					List<Integer> needParts = partList.get(next); // 다음 부품을 만들기 위해 필요한 부품들
					int size1 = needParts.size();
					for (int i=0;i<size1;i++) {
						Integer needPart = needParts.get(i);
						if (base[needPart]) continue; // 기본 부품이라면 continue
						List<Integer> baseParts = partList.get(needPart); // 필요한 부품들이 가진 기본 부품들
						int size2 = baseParts.size();
						for (int j=0;j<size2;j++) {
							Integer basePart = baseParts.get(j);
							// 필요한 부품들이 가진 기본 부품 * 부품의 개수
							arr[next][basePart] += arr[needPart][basePart] * arr[next][needPart]; 
							// 존재하는 기본 부품이 아니라면 추가
							if (!needParts.contains(basePart)) needParts.add(basePart); 
						}
						// 필요한 부품들이 가진 기본 부품을 추가해줬기 때문에 중간 부품은 삭제
						needParts.remove(i); 
						i--; size1--; // 하나를 삭제했으니 인덱스와 사이즈도 삭제
					}
				}
			}
		}
	}
}
