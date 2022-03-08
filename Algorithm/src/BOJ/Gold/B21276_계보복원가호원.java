package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B21276_계보복원가호원 {
	static int N, M, inDegree[], toIdx, fromIdx;
	static Map<String, Integer> searchIdx = new HashMap<>(); // 이름 -> 인덱스
	static Map<Integer, String> searchName = new HashMap<>(); // 인덱스 -> 이름
	static List<List<Integer>> list = new ArrayList<>(), child = new ArrayList<>(); // 정보, 자식 리스트
	static Queue<Integer> queue = new LinkedList<>(); // 위상 정렬을 돌리기 위한 큐
	static PriorityQueue<String> pq = new PriorityQueue<>(), ancestor = new PriorityQueue<>(), childName = new PriorityQueue<>(); // 사전순으로 정렬하기 위해 사용
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			String name = st.nextToken();
			searchIdx.put(name, i); // 이름으로 인덱스 찾기 위함
			searchName.put(i, name); // 인덱스로 이름 찾기 위함
			pq.offer(name); // 이름을 사전 순으로 정렬하기 위함
			list.add(new ArrayList<>());
			child.add(new ArrayList<>());
		}		
		inDegree = new int[N];
		M = Integer.parseInt(br.readLine());
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			toIdx = searchIdx.get(st.nextToken()); // 자식의 인덱스
			fromIdx = searchIdx.get(st.nextToken()); // 조상의 인덱스
			inDegree[toIdx]++;
			list.get(fromIdx).add(toIdx);
		}
		topology();
		// 출력
		sb.append(ancestor.size()+"\n"); // 
		
		while(!ancestor.isEmpty()) {
			sb.append(ancestor.poll()+" ");
		}
		sb.append("\n");
		
		while(!pq.isEmpty()) { // 사전순으로 이름 뽑아서 자식 찾기
			String key = pq.poll();
			int idx = searchIdx.get(key); // 부모 이름 인덱스
			sb.append(key+" "+child.get(idx).size()+" "); // 이름과 자식 개수 저장
			for (int child : child.get(idx)) { // 자식들
				String name = searchName.get(child); 
				childName.add(name);
			}
			while(!childName.isEmpty()) {
				sb.append(childName.poll()+" "); // 사전순으로 정렬된 자식 이름 저장
			}
			sb.append("\n");
			childName.clear(); // 초기화
		}
		 
		System.out.println(sb);
	}
	
	static void topology() {
		for (int i=0;i<N;i++) {
			if (inDegree[i] == 0) { // 진입 차수가 0 == 조상이 없다 == 선조
				queue.offer(i);
				ancestor.offer(searchName.get(i)); // 선조 저장
			}
		} 
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : list.get(now)) {
				if (--inDegree[next] == 0) { // 진입 차수가 0 == next는 now의 부모
					queue.offer(next);
					child.get(now).add(next); // 자식 저장
				}
			}
		}
	}
}