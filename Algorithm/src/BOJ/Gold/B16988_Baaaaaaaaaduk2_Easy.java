package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16988_Baaaaaaaaaduk2_Easy {
	static int R, C, MAX, killCnt, nowCnt, map[][], select[], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean visit[][], flag;
	static List<int[]> list, otherList;
	static Queue<int[]> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		otherList = new ArrayList<>();
		queue = new LinkedList<>();
		map = new int[R][C];
		select = new int[2];
		MAX = Integer.MIN_VALUE;
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0) list.add(new int[] {r, c});
				else if (map[r][c] == 2) otherList.add(new int[] {r, c});
			}
		}
		combination(0, 0);
		System.out.println(MAX==Integer.MIN_VALUE?0:MAX);
	}
	
	static void combination(int cnt, int start) {
		if (cnt == 2) {
			nowCnt = 0;
			visit = new boolean[R][C];
			for (int i=0;i<2;i++) {
				int[] now = list.get(select[i]);
				map[now[0]][now[1]] = 1;
			}
			
			for (int i=0;i<otherList.size();i++) {
				int[] now = otherList.get(i);
				if (!visit[now[0]][now[1]]) {
					flag = true;
					killCnt = 1;
					queue.offer(new int[] {now[0], now[1]});
					visit[now[0]][now[1]] = true;
					bfs();
					if (flag) nowCnt += killCnt;
				}
			}
			MAX = Math.max(MAX, nowCnt);
			for (int i=0;i<2;i++) {
				int[] now = list.get(select[i]);
				map[now[0]][now[1]] = 0;
			}
			queue.clear();
			return;
		}
		
		for (int i=start;i<list.size();i++) {
			select[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
	
	static void print() {
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int d=0;d<4;d++) {
				int nr = now[0] + dir[d][0];
				int nc = now[1] + dir[d][1];
				if (!isIn(nr, nc) || visit[nr][nc] || map[nr][nc] != 2) {
					if (isIn(nr, nc) && map[nr][nc] == 0) flag = false;
					continue;
				}
				
				visit[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
				killCnt++;
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
