package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17129_윌리암슨수액빨이딱다구리가정보섬에올라온이유 {
	static int R, C, cnt, ans;
	static int[][] map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<int[]> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c)-'0';
				if (map[r][c]==2) {
					map[r][c] = 1;
					queue.offer(new int[] {r, c});
				}
			}
		}
		if (bfs() > 0) System.out.println("TAK\n"+(cnt+1));
		else System.out.println("NIE");
	}
	
	static int bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i=0;i<size;i++) {
				int[] now = queue.poll();
				for (int d=0;d<4;d++) {
					int nr = now[0]+deltas[d][0];
					int nc = now[1]+deltas[d][1];
					if (isIn(nr, nc) && map[nr][nc]!=1) {
						if (map[nr][nc]!=0) return cnt;
						map[nr][nc] = 1;
						queue.offer(new int[] {nr, nc});
					}
				}
			}
			cnt++;
		}
		
		return 0;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
