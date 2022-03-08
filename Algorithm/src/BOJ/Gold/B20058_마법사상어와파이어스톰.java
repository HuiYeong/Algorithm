package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B20058_마법사상어와파이어스톰 {
	static int N, Q, pow, nowQ, ans, MAX, cnt;
	static int[][] map, tempMap, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visit;
	static Queue<int[]> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		pow = (int) Math.pow(2, N);
		map = new int[pow][pow];
		tempMap = new int[pow][pow];
		visit = new boolean[pow][pow];
		MAX = Integer.MIN_VALUE;
		queue = new LinkedList<>();
		for (int r=0;r<pow;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<pow;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int q=0;q<Q;q++) {
			nowQ = Integer.parseInt(st.nextToken());
			sectionDivide(0, 0, pow);
		 	copyMap(); // 회전 시킨 맵 복사
		 	checkIce(); // 얼음 검사
		} 
		
		// 남아 있는 얼음 
		remainIce();
		bfsReady();
		if (MAX == Integer.MIN_VALUE) MAX = 0;
		System.out.println(ans+"\n"+MAX);
	}
	
	static void sectionDivide(int start, int end, int length) { // 구간 나누기
		if (length == (int) Math.pow(2, nowQ)) {
			rotateIce(start, end, length);
			return;
		}
		int size = length/2;
		sectionDivide(start, end, size);
		sectionDivide(start, end+size, size);
		sectionDivide(start+size, end, size);
		sectionDivide(start+size, end+size, size);
	}
	
	static void rotateIce(int start, int end, int length) { // 얼음 회전
		int i = start, j = end+length;
		for (int r=start;r<start+length;r++) {
			i = start; j = j-1;
			for (int c=end;c<end+length;c++) {
				tempMap[i++][j] = map[r][c];
			}
		}
	}
	
	static void copyMap() {
		for (int r=0;r<pow;r++) {
			for (int c=0;c<pow;c++) {
				map[r][c] = tempMap[r][c];
			}
		}
	}
	
	static void checkIce() {
		for (int r=0;r<pow;r++) {
			for (int c=0;c<pow;c++) {
				if (map[r][c] > 0) {
					int iceCnt = 0;
					for (int d=0;d<4;d++) {
						int nr = r+dir[d][0];
						int nc = c+dir[d][1];
						if (!isIn(nr, nc) || map[nr][nc] == 0) continue;
						iceCnt++;
					}
					if (iceCnt<3) queue.offer(new int[] {r, c});
				}
			}
		}
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			map[now[0]][now[1]]--;
		}
	}
	
	static void remainIce() {
		for (int r=0;r<pow;r++) {
			for (int c=0;c<pow;c++) {
				ans += map[r][c];
			}
		}
	}
	
	static void bfsReady() {
		for (int r=0;r<pow;r++) {
			for (int c=0;c<pow;c++) {
				if (map[r][c] > 0) {
					queue.add(new int[] {r, c});
					visit[r][c] = true;
					cnt = 1;
					bfs();
				}
			}
		}
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int d=0;d<4;d++) {
				int nr = now[0] + dir[d][0];
				int nc = now[1] + dir[d][1];
				if (!isIn(nr, nc) || map[nr][nc] == 0 || visit[nr][nc]) continue;
				queue.offer(new int[] {nr, nc});
				visit[nr][nc] = true;
				cnt++;
			}
		}
		
		MAX = Math.max(MAX, cnt);
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<pow && nc>=0 && nc<pow;
	}
}
