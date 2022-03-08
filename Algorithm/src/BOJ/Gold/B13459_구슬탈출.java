package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class B13459_구슬탈출 {
	static int R, C, cnt, dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우
	static char[][] map;
	static boolean flag[];
	static Bead first, second;
	static Queue<Bead> beads;
	static Set<Visit> set;
	static class Bead {
		int r, c, isR;
		public Bead(int r, int c, int isR) {
			super();
			this.r = r;
			this.c = c;
			this.isR = isR;
		}
	}
	static class Visit {
		int rr, rc, br, bc;

		public Visit(int rr, int rc, int br, int bc) {
			super();
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + bc;
			result = prime * result + br;
			result = prime * result + rc;
			result = prime * result + rr;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Visit other = (Visit) obj;
			if (bc != other.bc)
				return false;
			if (br != other.br)
				return false;
			if (rc != other.rc)
				return false;
			if (rr != other.rr)
				return false;
			return true;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		beads = new LinkedList<>();
		set = new HashSet<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c]=='B') beads.offer(new Bead(r, c, 0));
				else if (map[r][c]=='R') beads.offer(new Bead(r, c, 1));
			}
		}
		System.out.println(bfs());
	}
	
	static int bfs() {
		while(!beads.isEmpty()) {
			int size = beads.size();
            cnt++;
			for (int s=0;s<size/2;s++) {
				Bead n1 = beads.poll();
				Bead n2 = beads.poll();
				
				for (int d=0;d<4;d++) {
					map[n1.r][n1.c] = n1.isR==1?'R':'B';
					map[n2.r][n2.c] = n2.isR==1?'R':'B';
					first = new Bead(n1.r, n1.c, n1.isR); second = new Bead(n2.r, n2.c, n2.isR);
					if ((d==0 && n1.c == n2.c && n1.r > n2.r) ||
							(d==1 && n1.c == n2.c && n1.r < n2.r) ||
							(d==2 && n1.r == n2.r && n1.c > n2.c) ||
							(d==3 && n1.r == n2.r && n1.c < n2.c)) {
						first = new Bead(n2.r, n2.c, n2.isR); second = new Bead(n1.r, n1.c, n1.isR);
					}
						
					flag = new boolean[2];
					first = move(first, d);
					second = move(second, d);
					map[first.r][first.c] = '.';
					map[second.r][second.c] = '.';
					
					if (!flag[0] && flag[1]) return cnt;
					else if (!flag[0] && !flag[1]) {
						Visit visit = null;
						if (first.isR == 1) visit = new Visit(first.r, first.c, second.r, second.c); 
						else visit = new Visit(second.r, second.c, first.r, first.c); 
						if (!set.contains(visit)) {
							set.add(visit);
							beads.offer(first);
							beads.offer(second);
						}
					}
				}
			}
		}
		return -1;
	}
	
	static Bead move(Bead now, int d) {
		int r = now.r, c = now.c;
		while(true) {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			if (!isIn(nr, nc) || map[nr][nc] != '.') {
				map[now.r][now.c] = '.'; 
				if (map[nr][nc] == 'O') flag[now.isR] = true;
				else map[r][c] = now.isR == 1?'R':'B';
				now.r = r; now.c = c;
				return now;
			}
			r = nr; c = nc;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
