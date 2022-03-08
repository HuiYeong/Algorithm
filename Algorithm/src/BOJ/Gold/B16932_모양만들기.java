package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class B16932_모양만들기 {
	static int R, C, labelCnt, size, MAX, cnt;
	static int[][] map, label, deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] labelCheck;
	static List<Integer> sizeList;
	static Set<Integer> labelSet;
	static Queue<Move> labelQ;
	static class Move {
		int r, c;

		public Move(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Move [r=" + r + ", c=" + c + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		label = new int[R][C];
		labelCheck = new boolean[R][C];
		labelQ = new LinkedList<>();
		sizeList = new ArrayList<>();
		sizeList.add(0);
		labelCnt = 1;
		MAX = Integer.MIN_VALUE;
		
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력완료
		
		labeling();
		System.out.println(bruteForce());
	}
	
	static int bruteForce() {
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]==0) {
					labelSet = new HashSet<>();
					cnt = 0;
					for (int d=0;d<4;d++) {
						int nr = r+deltas[d][0];
						int nc = c+deltas[d][1];
						if (isIn(nr, nc) && map[nr][nc]!=0) {
							if (!labelSet.contains(label[nr][nc])) {
								labelSet.add(label[nr][nc]);
								cnt+=sizeList.get(label[nr][nc]);
							}
						}
					}
					MAX = Math.max(MAX, cnt);
				}
			}
		}
		
		return MAX+1;
	}
	
	static void labeling() {
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]==1 && !labelCheck[r][c]) {
					size = 1;
					labelCheck[r][c] = true;
					label[r][c] = labelCnt;
					labelQ.offer(new Move(r, c));
					bfsLabel();
					labelCnt++;
					sizeList.add(size);
				}
			}
		}
	}
	
	static void bfsLabel() {
		while(!labelQ.isEmpty()) {
			Move now = labelQ.poll();
			for (int d=0;d<4;d++) {
				int nr = now.r+deltas[d][0];
				int nc = now.c+deltas[d][1];
				if (isIn(nr, nc) && map[nr][nc]==1 && !labelCheck[nr][nc]) {
					labelCheck[nr][nc] = true;
					label[nr][nc] = labelCnt;
					labelQ.offer(new Move(nr, nc));
					size++;
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
