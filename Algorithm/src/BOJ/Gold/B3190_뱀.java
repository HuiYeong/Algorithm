package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3190_뱀 {
	static int N, cnt;
	static boolean flag;
	static int[][] map, deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우 하 좌 상 
	static Map<Integer, Character> snakeDir;
	static Queue<Move> queue;
	static class Move {
		int r, c, dir;
		boolean tail;

		public Move(int r, int c, int dir, boolean tail) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.tail = tail;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		map[0][0] = 1; // 뱀
		queue = new LinkedList<>();
		queue.offer(new Move(0, 0, 0, false)); // 뱀 앞
		queue.offer(new Move(0, 0, 0, true)); // 뱀 뒤
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 2; // 사과
		}
		snakeDir = new HashMap<>(); // 뱀의 방향 변환 정보
		int L = Integer.parseInt(br.readLine());
		for (int l=0;l<L;l++) {
			st = new StringTokenizer(br.readLine());
			snakeDir.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		System.out.println(snakeMove());
	}
	
	static int snakeMove() {
		while(!queue.isEmpty()) {
			flag = false;
			for (int s=0;s<2;s++) {
				Move now = queue.poll();
				
				if(snakeDir.containsKey(cnt) && !now.tail) { // 뱀의 앞이고 cnt초에 해당하는 뱀의 방향 정보가 존재할 때
					if (snakeDir.get(cnt)=='L') now.dir = (now.dir+3)%4; // 왼쪽으로 90도
					else now.dir = (now.dir+1)%4; // 오른쪽으로 90도
					map[now.r][now.c] = -(now.dir+1); // 맵에 방향 값 넣어주기
				}
				
				if (now.tail && map[now.r][now.c]<0) { // 뱀의 꼬리이고 방향 값이 존재할 때
					now.dir = -(map[now.r][now.c]+1); // 해당 방향으로 바꿔주고
					map[now.r][now.c] = 0; // 맵에 있는 방향 값 없애주기
				}
				
				int nr = now.r+deltas[now.dir][0];
				int nc = now.c+deltas[now.dir][1];
				
				if (!now.tail && (!isIn(nr, nc) || map[nr][nc]==1)) return cnt+1; // 뱀의 앞이고 범위를 벗어나거나 뱀의 일부분일 때 끝 
				
				if (!now.tail) { // 뱀의 앞 일 때
					if (map[nr][nc]==2) flag = true; // 사과라면 flag는 true
					map[nr][nc] = 1; 
					queue.offer(new Move(nr, nc, now.dir, now.tail));
				} else {
					if (!flag) { // 사과가 없었다면 꼬리 이동
						map[now.r][now.c] = 0; // 원래 꼬리 지워주고
						queue.offer(new Move(nr, nc, now.dir, now.tail)); // 새로운 꼬리 추가
					} else queue.offer(now); // 사과가 있었다면 꼬리는 움직이지 않기 때문에 그대로 값을 넣어줌
				}
			}
			cnt++;
		}
		return 0;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}