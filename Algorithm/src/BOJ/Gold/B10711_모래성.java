package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B10711_모래성 {
	static int R, C, ans, size, cnt, sandCnt[][], dir[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
	static char[][] map;
	static Queue<int[]> queue, destoryQ, sandQ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		sandCnt = new int[R][C];
		destoryQ = new LinkedList<>();
		sandQ = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c] == '.') sandQ.offer(new int[] {r, c});
				else sandCnt[r][c] = map[r][c]-'0';
			}
		}
		
		while(true) {
			sandCastle();
			if (!sandDestory()) break;
			
			ans++;
		}
		
		System.out.println(ans);
	}
	
	static boolean sandDestory() {
		size = destoryQ.size();
		while(!destoryQ.isEmpty()) {
			int[] now = destoryQ.poll();
			map[now[0]][now[1]] = '.';
		}
		return size>0?true:false;
	}
	
	static void sandCastle() {
		size = sandQ.size();
		for (int s=0;s<size;s++) {
			int[] now = sandQ.poll();
			for (int d=0;d<dir.length;d++) {
				int nr = now[0] + dir[d][0];
				int nc = now[1] + dir[d][1];
				if (!isIn(nr, nc) || map[nr][nc] == '.') continue;
				
				if (sandCnt[nr][nc]-1 == 0) {
					destoryQ.offer(new int[] {nr, nc});
					sandQ.offer(new int[] {nr, nc});
				}
				sandCnt[nr][nc]--;
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
