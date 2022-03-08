package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B23290_마법사상어와복제 {
	static int M, S, s, ans, cnt, MAX, flagMap[][], fishCnt[][], temp[][][], fishMap[][][], smellMap[][], shark[], fishDir[][] = {{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}}, sharkDir[][] = {{0, 0}, {-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static PriorityQueue<String> prioriyQ;
	static Queue<int[]> copyQ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		prioriyQ = new PriorityQueue<>();
		copyQ = new LinkedList<>();
		smellMap = new int[4][4];
		fishCnt = new int[4][4];
		fishMap = new int[4][4][9];
		flagMap = new int[4][4];
		
		for (int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			fishMap[r][c][d]++; fishCnt[r][c]++;
		}
		st = new StringTokenizer(br.readLine());
		shark = new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
		// 입력 완료
		
		for (s=1;s<=S;s++) {
			temp = new int[4][4][9];
			fishCopy();
			fishMove(); // 물고기 이동
			sharkMove(); // 상어 이동
			smellRemove(); // 냄새 제거
			fishDuplicate(); // 물고기 복제
		}
		
		for (int r=0;r<4;r++) {
			for (int c=0;c<4;c++) {
				ans += fishCnt[r][c];
			}
		}
		System.out.println(ans);
	}
	
	static void fishCopy() {
		for (int r=0;r<4;r++) {
			for (int c=0;c<4;c++) {
				for (int d=1;d<9;d++) {
					copyQ.offer(new int[] {r, c, d, fishMap[r][c][d]});
				}
			}
		}
	}
	
	static void fishMove() {
		for (int r=0;r<4;r++) {
			for (int c=0;c<4;c++) {
				for (int d=1;d<9;d++) {
					if (fishMap[r][c][d]>0) fishMoveGo(r, c, d);
				}
			}
		}
		
		for (int r=0;r<4;r++) {
			for (int c=0;c<4;c++) {
				fishMap[r][c] = temp[r][c].clone();
			}
		}
	}
	
	static void fishMoveGo(int r, int c, int d) {
		int nd = d;
		for (int k=1;k<9;k++) {
			int nr = r + fishDir[nd][0];
			int nc = c + fishDir[nd][1];
			if (!isIn(nr, nc) || (nr == shark[0] && nc == shark[1]) || smellMap[nr][nc] != 0) nd = (nd-1)==0 ? 8:nd-1; // 방향 옮기기
			else {
				temp[nr][nc][nd] += fishMap[r][c][d];
				fishCnt[r][c] -= fishMap[r][c][d];
				fishCnt[nr][nc] += fishMap[r][c][d];
				return;
			}
		}
		temp[r][c][d] += fishMap[r][c][d];
	}
	
	static void sharkMove() {
		orderDecide(); // 이동 순서 결정
		String[] now = prioriyQ.poll().split(" ");
		for (int i=0;i<3;i++) {
			int nr = shark[0] + sharkDir[Integer.parseInt(now[i])][0];
			int nc = shark[1] + sharkDir[Integer.parseInt(now[i])][1];
			if (fishCnt[nr][nc] != 0) smellMap[nr][nc] = s;
			
			for (int j=1;j<9;j++) {
				fishMap[nr][nc][j] = 0;
			}
			fishCnt[nr][nc] = 0;
			shark[0] = nr; shark[1] = nc;
		}
	}
	
	static void fishDuplicate() {
		while(!copyQ.isEmpty()) {
			int[] copy = copyQ.poll();
			fishMap[copy[0]][copy[1]][copy[2]] += copy[3];
			fishCnt[copy[0]][copy[1]] += copy[3]; 
		}
	}
	
	static void smellRemove() {
		for (int r=0;r<4;r++) {
			for (int c=0;c<4;c++) {
				if (smellMap[r][c] != 0 && s-2 == smellMap[r][c]) smellMap[r][c] = 0; 
			}
		}
	}
	
	static void orderDecide() {
		MAX = Integer.MIN_VALUE;
		for (int i=1;i<=4;i++) {
			cnt = 0;
			int nr1 = shark[0] + sharkDir[i][0];
			int nc1 = shark[1] + sharkDir[i][1];
			if (!repeat(nr1, nc1, 1)) continue;
			for (int j=1;j<=4;j++) {
				int nr2 = nr1 + sharkDir[j][0];
				int nc2 = nc1 + sharkDir[j][1];
				if (!repeat(nr2, nc2, 2)) continue;
				for (int k=1;k<=4;k++) {
					int nr3 = nr2 + sharkDir[k][0];
					int nc3 = nc2 + sharkDir[k][1];
					if (!repeat(nr3, nc3, 3)) continue;
					
					if (cnt >= MAX) {
						if (cnt > MAX) prioriyQ.clear();
						String step = i + " " + j + " " + k;
						prioriyQ.offer(step);
						MAX = cnt;
					}
					if (flagMap[nr3][nc3] == 3) {
						cnt -= fishCnt[nr3][nc3];
						flagMap[nr3][nc3] = 0;
					}
				}
				if (flagMap[nr2][nc2] == 2) {
					cnt -= fishCnt[nr2][nc2];
					flagMap[nr2][nc2] = 0;
				}
			}
			if (flagMap[nr1][nc1] == 1) {
				cnt -= fishCnt[nr1][nc1];
				flagMap[nr1][nc1] = 0;
			}
		}
	}
	
	static boolean repeat(int nr, int nc, int idx) {
		if (!isIn(nr, nc)) return false;
		
		if (flagMap[nr][nc] == 0) {
			cnt += fishCnt[nr][nc];
			flagMap[nr][nc] = idx;
		}
		return true;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<4 && nc>=0 && nc<4;
	}
}
