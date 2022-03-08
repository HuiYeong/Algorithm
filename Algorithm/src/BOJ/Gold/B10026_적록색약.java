package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B10026_적록색약 {
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, gP, bP;
	static char[][] drawing;
	static int[][] check;
	static Queue<int[]> bfs;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		drawing = new char[N][N];
		check = new int[N][N];
		for (int i=0;i<N;i++) {
			String input = br.readLine();
			drawing[i] = input.toCharArray();
		}
		loop('g'); // g : 적록색약 아닌 사람
		
		check = new int[N][N]; // 방문체크 초기화
		loop('b'); // b : 적록색약인 사람
		
		sb.append(gP+" "+bP);
		System.out.println(sb);
	}
	
	static void loop(char who) {
		bfs = new LinkedList<>();
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (check[i][j]==0) { // 방문하지 않았다면
					if (who=='b') bP++; // 적록색약인 사람이면 적록색약 카운트 ++
					else gP++; // 적록색약이 아닌 사람이면 적록색약 X 카운트++
					check[i][j] = 1;
					bfsL(i, j, drawing[i][j], who); // bfs -> 해당 값 넘겨줌
				}
			}
		}
	}
	
	static void bfsL(int r, int c, char search, char who) {
		bfs.offer(new int[] {r, c});
		while(!bfs.isEmpty()) {
			int[] now = bfs.poll();
			for (int d=0;d<4;d++) {
				int nr = now[0]+deltas[d][0];
				int nc = now[1]+deltas[d][1];
				if (isIn(nr, nc) && check[nr][nc]==0) {
					if (who=='g' && drawing[nr][nc]==search) { // 적록색약이 아닌 사람이라면 해당 값과 탐색 값이 일치할 때만 추가
						check[nr][nc] = 1;
						bfs.offer(new int[] {nr, nc});
					}
					else if (who=='b' && ((drawing[nr][nc]=='R'||drawing[nr][nc]=='G') && (search=='R' ||search=='G') ||
							drawing[nr][nc]=='B' && search=='B')) {  // 적록색약인 사람이라면 R, G를 구분하지 못하기 때문에 조건 추가
						check[nr][nc] = 1;
						bfs.offer(new int[] {nr, nc});
					}
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
