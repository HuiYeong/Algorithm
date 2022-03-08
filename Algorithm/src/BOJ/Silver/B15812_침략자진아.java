package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B15812_침략자진아 {
	static int R, C, MIN, town, townCnt, ans;
	static int[][] map, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visit;
	static Queue<int[]> bfs;
	static int[] select;
	static List<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		select = new int[2];
		list = new LinkedList<>();
		
		MIN = Integer.MAX_VALUE;
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c)-'0';
				if (map[r][c]==0) list.add(new int[] {r, c});
				else town++;
			}
		}
		combination(0, 0);
		System.out.println(MIN);
	}
	
	static void combination(int cnt, int start) {
		if (cnt == 2) {
			townCnt = 0; ans = 0;
			visit = new boolean[R][C];
			bfs = new LinkedList<>();
//			System.out.println(Arrays.toString(select));
			for (int i=0;i<2;i++) {
				int[] selected = list.get(select[i]);
//				System.out.println("i : "+selected[0]+", j : "+selected[1]);
				bfs.offer(selected);
				visit[selected[0]][selected[1]] = true;
			}
        	bruteforce();
//        	System.out.println("MIN : "+MIN+", ans : "+ans);
        	MIN = Math.min(MIN, ans);
        	return;
        }
        
        for (int i=start;i<list.size();i++) {
        	select[cnt] = i;
        	combination(cnt+1, i+1);
        }
	}
	
	static void bruteforce() {
		while(!bfs.isEmpty()) {
			if (townCnt == town) break;
			int size = bfs.size();
			for (int s=0;s<size;s++) {
				int[] now = bfs.poll();
				for (int d=0;d<4;d++) {
					int nr = now[0] + dir[d][0];
					int nc = now[1] + dir[d][1];
					if (isIn(nr, nc) && !visit[nr][nc]) {
						if (map[nr][nc] == 1) townCnt++;
						visit[nr][nc] = true;
						bfs.offer(new int[] {nr, nc});
					}
				}
			}
			ans++;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
