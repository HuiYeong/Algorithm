package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16985_Maaaaaaaaaze {
	static int cnt, MIN;
	static boolean flag;
	static boolean[] visit;
	static int[][][] map, map90, map180, map270, bfsMap;
	static int[] selectRotate, selectOrder, dirK = {0, 0, 0, 0, -1, 1}, dirR = {-1, 1, 0, 0, 0, 0}, dirC = {0, 0, -1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[5][5][5];
		map90 = new int[5][5][5];
		map180 = new int[5][5][5];
		map270 = new int[5][5][5];
		bfsMap = new int[5][5][5];
		for (int k=0;k<5;k++) {
			for (int r=0;r<5;r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c=0;c<5;c++) {
					map[k][r][c] = Integer.parseInt(st.nextToken());
				}
			}
		}
		// 입력 완료
		
		// 확률 돌리기 전에 미리 돌려부러~ 
		for (int i=1;i<=3;i++) {
			rotate(i);
		}
		
		MIN = Integer.MAX_VALUE;
		selectRotate = new int[5];
		selectOrder = new int[5];
		visit = new boolean[5];
		permutationRotate(0);
		
		if (MIN == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(MIN);
	}
	
	static void rotate(int type) {
		int[][][] tempMap = new int[5][5][5];
		
		for (int k=0;k<5;k++) {
			int i = 0, j = 5;
			for (int r=0;r<5;r++) {
				i = 0; j = j-1;
				for (int c=0;c<5;c++) {
					if (type == 1) tempMap[k][i++][j] = map[k][r][c];
					else if (type == 2) tempMap[k][i++][j] = map90[k][r][c];
					else tempMap[k][i++][j] = map180[k][r][c];
				}
			}
		}
		
		for (int k=0;k<5;k++) {
			for (int r=0;r<5;r++) {
				for (int c=0;c<5;c++) {
					if (type == 1) map90[k][r][c] = tempMap[k][r][c];
					else if (type ==2) map180[k][r][c] = tempMap[k][r][c];
					else map270[k][r][c] = tempMap[k][r][c];
				}
			}
		}
	}
	
	static void permutationRotate(int cntRotate) {
		if (cntRotate == 5) {
			permutationOrder(0);
			return;
		}
		
		for (int i=0;i<4;i++) {
			selectRotate[cntRotate] = i;
			permutationRotate(cntRotate+1);
		}
	}
	
	static void permutationOrder(int cntOrder) {
		if (cntOrder == 5) {
			makeMap();
			bfs();
			return;
		}
		
		for (int i=0;i<5;i++) {
			if (visit[i]) continue;
			
			selectOrder[cntOrder] = i;
			visit[i] = true;
			permutationOrder(cntOrder+1);
			visit[i] = false;
		}
	}
	
	static void makeMap() {
		for (int k=0;k<5;k++) {
			for (int r=0;r<5;r++) {
				for (int c=0;c<5;c++) {
					int type = selectRotate[k], order = selectOrder[k];
					if (type == 0) bfsMap[order][r][c] = map[k][r][c]; // 회전 x
					else if (type == 1) bfsMap[order][r][c] = map90[k][r][c]; // 90도 회전
					else if (type == 2) bfsMap[order][r][c] = map180[k][r][c]; // 180도 회전
					else if (type == 3) bfsMap[order][r][c] = map270[k][r][c]; // 270도 회전
				}
			}
		}
	}
	
	static void bfs() {
		if (bfsMap[0][0][0] != 1 || bfsMap[4][4][4] != 1) return;
		cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][] visit = new boolean[5][5][5];
		
		queue.offer(new int[] {0, 0, 0});
		visit[0][0][0] = true;
		
		while(!queue.isEmpty() && !flag) {
			int size = queue.size();
			for (int s=0;s<size;s++) {
				int[] now = queue.poll();
				if (now[0] == 4 && now[1] == 4 && now[2] == 4) {
					MIN = Math.min(MIN, cnt);
					break;
				}
				for (int d=0;d<6;d++) {
					int nk = now[0] + dirK[d], nr = now[1] + dirR[d], nc = now[2] + dirC[d];
					if (!isIn(nk, nr, nc) || bfsMap[nk][nr][nc] == 0 || visit[nk][nr][nc]) continue;
					
					queue.offer(new int[] {nk, nr, nc});
					visit[nk][nr][nc] = true;
				}
			}
			cnt++;
			if (cnt >= MIN) break;
		}
	}
	
	static boolean isIn(int nk, int nr, int nc) {
		return nk>=0 && nk<5 && nr>=0 && nr<5 && nc>=0 && nc<5;
	}
}
