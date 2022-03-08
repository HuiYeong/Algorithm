package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B6593_상범빌딩 {
	private static int[][] deltas = {{1, 0, 0}, {-1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
	private static Queue<rc> bfs;
	private static int L, R, C, ans;
	private static char[][][] map;
	private static StringBuilder sb;
	public static class rc {
		int i, r, c, ans;

		public rc(int i, int r, int c, int ans) {
			super();
			this.i = i;
			this.r = r;
			this.c = c;
			this.ans = ans;
		}

		@Override
		public String toString() {
			return i+" "+r+" "+c+" "+ans;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if (L==0 && R==0 && C==0) break;
			map = new char[L][R][C];
			bfs = new LinkedList<>();
			for (int i=0;i<L;i++) {
				for (int r=0;r<R;r++) {
					String input = br.readLine();
					for (int c=0;c<C;c++) {
						map[i][r][c] = input.charAt(c);
						if(map[i][r][c]=='S') bfs.offer(new rc(i, r, c, 0)); 
					}
				}
				String input = br.readLine();
			}
			// 입력완료
			ans = 0;
			boolean succ = true;
			succ = bfsL();
			if (succ) sb.append("Escaped in "+ans+" minute(s).\n");
			else sb.append("Trapped!\n");
		}
		System.out.print(sb);
	}
	
	private static boolean bfsL() {
		boolean flag = false;
		outer:while(!bfs.isEmpty()) {
			rc now = bfs.poll();
			for (int d=0;d<deltas.length;d++) {
				int ni = now.i+deltas[d][0];
				int nr = now.r+deltas[d][1];
				int nc = now.c+deltas[d][2];
				ans = now.ans;
				if (isIn(ni, nr, nc) && map[ni][nr][nc]=='.') {
					map[ni][nr][nc]='#';
					bfs.offer(new rc(ni, nr, nc, ans+1));
				} else if (isIn(ni, nr, nc) && map[ni][nr][nc]=='E') {
					flag = true;
					ans++;
					break outer;
				}
 			}
		}
		return flag;
	}
	
	private static boolean isIn(int ni, int nr, int nc) {
		return ni>=0 && ni<L && nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
