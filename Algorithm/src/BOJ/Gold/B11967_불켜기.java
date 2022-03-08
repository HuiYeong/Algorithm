package BOJ.Gold;
import java.io.*;
import java.util.*;

public class B11967_불켜기 {
	static int N, M, cnt;
	static boolean flag;
	static int[][] deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] switchOn, isLight, visited;
	static Queue<Loc> move, lamp;
	static HashMap<Loc, List<Loc>> switches;
	static class Loc {
		int r, c;

		public Loc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
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
			Loc other = (Loc) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		switchOn = new boolean[N+1][N+1];
		isLight = new boolean[N+1][N+1];
		visited = new boolean[N+1][N+1];
		move = new LinkedList<>();
		lamp = new LinkedList<>();
		switches = new HashMap<>();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			Loc start = new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Loc dis = new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			List<Loc> list = new ArrayList<>();
			if (switches.containsKey(start)) {
				list = switches.get(start);
				list.add(dis);
			} else list.add(dis);
			switches.put(start, list);	
		}
		// 입력완료
		move.offer(new Loc(1, 1));
		isLight[1][1] = true;
		visited[1][1] = true;
		cnt = 1;
		bfs();
	}
	
	static void bfs() {
		while(!move.isEmpty()) {
			Loc now = move.poll();
			// 불 켜기
			if (switches.containsKey(now)) {
				List<Loc> next = (List<Loc>) switches.get(now);
				for (int i=0;i<next.size();i++) {
					Loc go = next.get(i);
					if (!isLight[go.r][go.c]) {
						isLight[go.r][go.c] = true;
						if (isMove(go)) {
							visited[go.r][go.c] = true;
							move.offer(go);
						} else lamp.offer(go);
						cnt++; 
					}
				}	
			}
			
			int size = lamp.size();
			for (int i=0;i<size;i++) {
				Loc go = lamp.poll();
				if (isMove(go)) {
					visited[go.r][go.c] = true;
					move.offer(go);
				} else lamp.offer(go);
			}
		}
		
		System.out.println(cnt);
	}
	
	static boolean isMove(Loc go) {
		for (int d=0;d<4;d++) {
			int nr = go.r+deltas[d][0];
			int nc = go.c+deltas[d][1];
			if (isIn(nr, nc) && visited[nr][nc]) return true;
		}
		return false;
	}

	static boolean isIn(int nr , int nc) {
		return nr>0 && nr<=N && nc>0 && nc<=N;
	}
}	
