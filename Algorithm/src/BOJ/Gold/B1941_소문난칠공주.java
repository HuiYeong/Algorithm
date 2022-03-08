package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class B1941_소문난칠공주 {
	static int ans, cntS, bfsCnt, select[] = new int[7], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char arr[][] = new char[5][5];
	static boolean visit[][], search[][];
	static List<int[]> list = new ArrayList<>();
	static Queue<int[]> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int r=0;r<5;r++) {
			String input = br.readLine();
			for (int c=0;c<5;c++) {
				arr[r][c] = input.charAt(c);
				list.add(new int[] {r, c}); // 조합 돌리기 위해 리스트에 위치 추가
			}
		}
		combination(0, 0); // 조합
		System.out.println(ans);
	}
	
	static void combination(int cnt, int start) {
		if (cnt == 7) { // 7개 구하면
			search = new boolean[5][5]; cntS = 0;
			for (int i=0;i<7;i++) {
				int[] now = list.get(select[i]);
				if (arr[now[0]][now[1]]=='S') cntS++; // 임다연파 개수 카운트
				search[now[0]][now[1]] = true; // search 배열에 선택한 위치 저장
			}
			 
			if (cntS<4) return; // 임다연파가 4명 이하라면 안됨
			queue.clear(); // 큐 초기화
			visit = new boolean[5][5]; bfsCnt = 1;
			int[] first = list.get(select[0]);
			visit[first[0]][first[1]] = true;
			queue.offer(new int[] {first[0], first[1]});
			if (bfs()) ans++; // bfs 돌린 후 true면 개수 카운트
			return;
		}
		
		for (int i=start;i<list.size();i++) {
			select[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
	
	static boolean bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-->0) {
				int[] now = queue.poll();
				for (int d=0;d<4;d++) {
					int nr = now[0] + dir[d][0];
					int nc = now[1] + dir[d][1];
					// 내가 갈 방향이 맵을 벗어났거나 이미 방문했거나 선택된 위치가 아니라면 못감
					if (!isIn(nr, nc) || visit[nr][nc] || !search[nr][nc]) continue;
					visit[nr][nc] = true;
					bfsCnt++;
					if (bfsCnt==7) return true; // 내가 선택한 7군데를 다 방문할 수 있다면 모두 인접해 있는 것 
					queue.offer(new int[] {nr, nc});
				}
			}
		}
		return false; // 내가 선택한 7군데를 모두 방문하지 못한다면 인접하지 못한 
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<5 && nc>=0 && nc<5;
	}
}
