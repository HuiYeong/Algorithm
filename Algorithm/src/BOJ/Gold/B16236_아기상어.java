package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16236_아기상어 {
	static int[][] map, visit, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
	static int N, cnt, totalCnt, sharkS, time, ans, count, sizeC;
	static Queue<shark> queue;
	static PriorityQueue<shark> fish;
	static class shark implements Comparable<shark>{
		int r, c, size;

		public shark(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(shark o) { // 제일 위쪽에 있는 물고기 먹어야됨, 여러개라면 제일 왼쪽에 있는 물고기 먹어야됨
			if (this.r==o.r) {  // 같은 행에 있는 물고기가 여러개라면
				count++; // 카운트 올려주기
			}
			if (count == 0)return this.r-o.r; // 제일 위에 있는 물고기가 먼저
			else {
				count = 0;
				return this.c-o.c; // 제일 위에 있는 물고기가 여러개라면 제일 왼쪽에 있는 물고기가 먼저
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new int[N][N];
		queue = new LinkedList<>();
		fish = new PriorityQueue<>();
		sharkS = 2; // 상어 처음 크기는 2
		for (int r=0;r<N;r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c]==9) {
					queue.offer(new shark(r, c));
					map[r][c]=0;
				}
			}
		}
		// 입력완료
		bfs();
		if (totalCnt==0) System.out.println(0); // 물고기 한마리도 못먹을 수 있음
		else System.out.println(ans);
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			time++; // 시간은 째깍째깍
			int size = queue.size();
			cnt = 0; // 현재 먹은 물고기 개수
			for (int i=0;i<size;i++) {
				shark now = queue.poll();
				visit[now.r][now.c] = 1; // 방문 했다!!!!
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc) && map[nr][nc]<=sharkS && visit[nr][nc]==0) { // 범위를 벗어나지 않고 현재 상어 크기보다 물고기 크기가 작고 방문하지 않았다면
						if (map[nr][nc]>0 && map[nr][nc]<sharkS) {
							cnt++; // 물고기 먹기
							fish.offer(new shark(nr, nc)); // 먹은 물고기 추가
						} else queue.offer(new shark(nr, nc)); // 못 먹었으면 이동만 해~~ 
						visit[nr][nc] = 1; // 방문 했다!!!!
					}
				}
			}
			if (cnt>0) { // 물고기를 먹었다면!!
				totalCnt++; // 총 먹은 물고기 개수 ++ (물고기 한마디도 못먹었으면 0 출력해줘야 하기 때문..)
				ans += time; // 물고기 먹은 시간 더해주기
				time = 0; // 시간은 다시 원점으로..
				sizeC++; // 현재까지 먹은 물고기 개수 ++
				if (sizeC==sharkS) { // 현재까지 먹은 물고기 개수와 상어의 크기가 같다면
					sharkS++; // 상어 크기 1 증가
					sizeC = 0; // 크기 증가했으니 물고기 개수는 다시 0
				}
				shark save = fish.poll(); // 내가 먹을 물고기는 하나 
				map[save.r][save.c] = 0; // 물고기가 있는 좌표는 비워주기
				queue = new LinkedList<>(); // 물고기 다시 처음부터 시작해야되니 새로 큐 생성
				queue.offer(save); // 먹은 물고기 좌표에 상어 있음
				fish = new PriorityQueue<>(); // 현재의 상어 위치에서 새로 먹으러 가야하니 물고기도 새로 우선순위 큐 생성
				visit = new int[N][N]; // 방문처리도 다시
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
