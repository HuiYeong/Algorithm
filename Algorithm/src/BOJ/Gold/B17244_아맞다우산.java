package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17244_아맞다우산 {	
	static int R, C, MIN, select[], dist[][], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char arr[][];
	static boolean visit[][], isSelect[];
	static List<int[]> list = new ArrayList<>();
	static Queue<int[]> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		dist = new int[7][7]; // S + E + X 최대 5개 == 7로 초기화
		list.add(new int[] {0, 0}); // 0번째는 무조건 S
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				arr[r][c] = input.charAt(c);
				if (arr[r][c] == 'S') {
					list.get(0)[0] = r; list.get(0)[1] = c;
				} else if (arr[r][c] == 'X') {
					// 사용하기 편리하도록 맵에 번호로 저장
					arr[r][c] = Character.forDigit(list.size(), 10); 
					list.add(new int[] {r, c});
				}
			}
		}
		distSearch(); // 거리 찾기
		select = new int[list.size()-1];
		isSelect = new boolean[list.size()];
		MIN = Integer.MAX_VALUE;
		permutation(0); // 순열
		System.out.println(MIN);
	}
	
	static void permutation(int cnt) {
		if (cnt == list.size()-1) {
			int sum = 0, start = 0; // 출발은 무조건 S부터
			for (int i=0;i<select.length;i++) {
				int now = select[i];
				// 현재 지점 -> 이동 지점까지의 거리 더해주기
				sum += dist[start][now];
				start = now; // 다음 시작 지점은 이동한 지점
			}
			sum += dist[start][list.size()]; // 마지막은 무조건 E
			MIN = Math.min(MIN, sum); // 최소 시간 구하기
			return;
		}
		
		for (int i=1;i<list.size();i++) {
			if (isSelect[i]) continue;
			select[cnt] = i;
			isSelect[i] = true;
			permutation(cnt+1);
			isSelect[i] = false;
		}
	}
	
	static void distSearch() {
		// S부터 구하기 == X가 없을 수도 있으므로 S -> E 까지의 거리도 구함
		for (int i=0;i<list.size();i++) {
			int[] now = list.get(i);
			visit = new boolean[R][C];
			visit[now[0]][now[1]] = true;
			queue.offer(now);
			bfs(i);
 		}
	}
	
	static void bfs(int number) {
		int cnt = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-->0) {
				int[] now = queue.poll();
				for (int d=0;d<4;d++) {
					int nr = now[0] + dir[d][0];
					int nc = now[1] + dir[d][1];
					if (!isIn(nr, nc) || arr[nr][nc] == '#' || visit[nr][nc]) continue;
					
					if (arr[nr][nc] == 'S') {
						// 현재 번호 -> S 까지의 거리 저장
						dist[0][number] = cnt;
						dist[number][0] = cnt;
					} else if (arr[nr][nc] == 'E') {
						// 현재 번호 -> E 까지의 거리 저장
						dist[number][list.size()] = cnt;
						dist[list.size()][number] = cnt;
					} else if (arr[nr][nc] != '.') {
						int next = arr[nr][nc]-'0';
						// 현재 번호 -> 이동 번호까지의 거리 저장
						dist[number][next] = cnt;
						dist[next][number] = cnt;
					}
					visit[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
			cnt++;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
