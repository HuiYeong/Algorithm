package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B7662_이중우선순위큐 {
	static int TC, K, value;
	static char menu;
	static PriorityQueue<Integer> MIN = new PriorityQueue<>(), MAX = new PriorityQueue<>(Collections.reverseOrder());
	// 최솟값 삭제 저장, 최댓값 삭제 저장
	static Map<Integer, Integer> minMap = new HashMap<>(), maxMap = new HashMap<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		TC = Integer.parseInt(br.readLine());
		while(TC-->0) {
			K = Integer.parseInt(br.readLine());
			for (int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				menu = st.nextToken().charAt(0);
				value = Integer.parseInt(st.nextToken());
				switch(menu) {
				case 'I':
					// 최댓값, 최솟값 우선순위 큐에 모두 저장
					MIN.offer(value);
					MAX.offer(value);
					break;
				case 'D':
					if (value == 1) check(false, MAX, MIN, maxMap, minMap);
					else check(false, MIN, MAX, minMap, maxMap);
					break;
				}
			}
			
			// 미처 못지운 값이 있을 수 있으므로 삭제해줌
			check(true, MAX, MIN, maxMap, minMap);
			check(true, MIN, MAX, minMap, maxMap);
			
			if (MAX.size() == 0) sb.append("EMPTY\n");
			else sb.append(MAX.peek()+" "+MIN.peek()+"\n");
			// 초기화
			minMap.clear();
			maxMap.clear();
			MIN.clear();
			MAX.clear();
		}
		System.out.println(sb);
	}
	
	static void check(boolean flag, PriorityQueue<Integer> now, PriorityQueue<Integer> other, Map<Integer, Integer> nowMap, Map<Integer, Integer> otherMap) {
		// 최솟값 삭제 예시
		while(!now.isEmpty()) {
			int poll = now.poll(); // 삭제할 값 꺼내기
			// 최댓값에서 이미 삭제한 값이라면
			if (otherMap.containsKey(poll)) {
				int cnt = otherMap.get(poll)-1;
				if (cnt == 0) otherMap.remove(poll); // 더 이상 존재하지 않는다면 삭제
				else otherMap.put(poll, cnt);
			} else { // 삭제한 값이 아니라면
				// 마지막에 삭제 체크 해주는 것이라면 삭제할 값이 없으므로 다시 큐에 삽입
				if (flag) now.offer(poll);
				// 최솟값을 삭제하는 것이라면 최솟값 삭제 맵에 해당 값 추가
				else nowMap.put(poll, nowMap.containsKey(poll)?nowMap.get(poll)+1:1);
				return;
			}
		}
	}
}
