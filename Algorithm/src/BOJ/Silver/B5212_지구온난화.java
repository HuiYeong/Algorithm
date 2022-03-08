package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B5212_지구온난화 {
	static int R, C, cnt, fR, eR, fC, eC;
	static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	static char[][] map, afterMap;
	static List<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		afterMap = new char[R][C];
		list = new ArrayList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				afterMap[r][c] = input.charAt(c);
				// 섬은 리스트에 전부 저장
				if (map[r][c]=='X') list.add(new int[] {r, c}); 
			}
		}
		afterYear();
		// 출력하기 위한 범위 구하기
		print(); 
		
		for (int r=fR;r<=eR;r++) {
			for (int c=fC;c<=eC;c++) {
				System.out.print(afterMap[r][c]);
			}
			System.out.println();
		}
	}
	
	static void afterYear() {
		for (int i=0;i<list.size();i++) {
			cnt = 0;
			int[] now = list.get(i);
			for (int d=0;d<4;d++) {
				int nr = now[0]+dir[d][0];
				int nc = now[1]+dir[d][1];
				// 주변 바다 개수 세기
				if (!isIn(nr, nc) || (isIn(nr, nc) && map[nr][nc]=='.')) cnt++; 
			}
			// 주변 바다가 3개 이상일 때 섬은 사라짐
			if (cnt>=3) afterMap[now[0]][now[1]] = '.'; 
		}
	}	
	
	static void print() {
		// fR : 출발 행, eR : 도착 행, fC : 출발 열, eC :도착 열
		fR = R; eR = -1; fC = C; eC = -1; 
		
		// 출발 행, 출발 열 구하기
		for (int r=0;r<R;r++) { // 가장 위
			for (int c=0;c<C;c++) { // 왼쪽에서 부터
				if (afterMap[r][c]=='X') {
					// 가장 먼저 섬을 발견하는 행이 출발 행
					if (fR==R) fR = r;
					// 가장 왼쪽에 있는 섬 구하기
					if (fC>c) fC = c; 
					break;
				}
			}
		}
		// 도착 행, 도착 열 구하기
		for (int r=R-1;r>=0;r--) { // 가장 밑 
			for (int c=C-1;c>=0;c--) { // 오른쪽에서 부터
				if (afterMap[r][c]=='X') { 
					// 밑에서부터 시작하여 가장 먼저 발견하는 행이 도착 행
					if (eR==-1) eR = r;
					// 가장 오른쪽에 있는 섬 구하기
					if (eC<c) eC = c; 
					break;
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
