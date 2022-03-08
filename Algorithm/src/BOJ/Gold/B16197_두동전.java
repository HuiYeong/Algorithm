package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16197_두동전 {
	static int R, C, coinNum, cnt, dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char map[][];
	static Queue<int[]> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c] == 'o') queue.offer(new int[] {r, c});
			}
		}
		System.out.println(bfs());
	}
	
	static int bfs() {
		while(!queue.isEmpty()) {
			if (cnt++ >= 10) return -1;
			int size = queue.size();
			for (int s=0;s<size/2;s++) {
				int[] now1 = queue.poll();
				int[] now2 = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr1 = now1[0] + dir[d][0];
					int nc1 = now1[1] + dir[d][1];
					int nr2 = now2[0] + dir[d][0];
					int nc2 = now2[1] + dir[d][1];
					if (!isIn(nr1, nc1) && !isIn(nr2, nc2)) continue;
					if ((!isIn(nr1, nc1) && isIn(nr2, nc2)) || (isIn(nr1, nc1) && !isIn(nr2, nc2))) return cnt;
					
					if (map[nr1][nc1] == '#') queue.offer(now1);
					if (map[nr2][nc2] == '#') queue.offer(now2);
					
					if (map[nr1][nc1] != '#') queue.offer(new int[] {nr1, nc1});
					if (map[nr2][nc2] != '#') queue.offer(new int[] {nr2, nc2});
				}
			}
		}
		
		return -1;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
