package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B14502_연구소 {
	static int R, C, MIN, danger;
	static int[] select;
	static int[][] lab, deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static List<Move> blank, virus;
	static class Move {
		int r, c;

		public Move(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return r+" "+c;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		lab = new int[R][C];
		blank = new ArrayList<>();
		virus = new ArrayList<>();
		int blankCnt = 0;
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				lab[r][c] = Integer.parseInt(st.nextToken());
				if (lab[r][c]==2) virus.add(new Move(r, c));
				else if (lab[r][c]==0) {
					blankCnt++;
					blank.add(new Move(r, c));
				}
			}
		}
		// 입력완료
		select = new int[3];
		MIN = Integer.MAX_VALUE;
		combination(0, 0);
		int labSize = blankCnt-3-MIN+virus.size();
		System.out.println(labSize);
	}
	
	static void combination(int cnt, int start) {
		if (cnt==3) {
			visited = new boolean[R][C];
			
			for (int i=0;i<3;i++) {
				int r = blank.get(select[i]).r;
				int c = blank.get(select[i]).c;
				visited[r][c] = true;
			}
			
			danger = 0;
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (!visited[r][c] && lab[r][c] == 2) {
						dfs(r, c);
					}
				}
			}
			MIN = Math.min(MIN, danger);
			return;
		}
		
		for (int i=start;i<blank.size();i++) {
			select[cnt] = i;
			combination(cnt+1, start+1);
		}
	}
	
	static void dfs(int r, int c) {
		if (MIN<danger) return;
		danger++;
		for (int d=0;d<4;d++) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc) && !visited[nr][nc] && lab[nr][nc] ==0) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
		
		return;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
