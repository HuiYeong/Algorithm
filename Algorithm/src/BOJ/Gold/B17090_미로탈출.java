package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17090_미로탈출 {
	static int R, C, cnt, d, dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char arr[][];
	static boolean visit[][], flag[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		visit = new boolean[R][C];
		flag = new boolean[R][C];
		for (int r=0;r<R;r++) {
			arr[r] = br.readLine().toCharArray();
		}
		
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (!visit[r][c]) dfs(r, c); // 방문 안한 위치만 dfs 처리
			}
		}
		System.out.println(cnt);
	}
	
	static boolean dfs(int r, int c) {
		char now = arr[r][c];
		visit[r][c] = true; // 방문 처리
		d = now=='U'?0:now=='D'?1:now=='L'?2:3; // 방향 설정
		int nr = r + dir[d][0];
		int nc = c + dir[d][1];
		
		if (!isIn(nr, nc)) { // 범위를 벗어나면 탈출 가능
			flag[r][c] = true; // 난 탈출!!!
			cnt++;
			return true; // 내 전 방문지도 탈출
		}
		else if (visit[nr][nc]) { // 만약 내가 갈 곳이 이미 방문한 곳이라면 
			if (flag[nr][nc]) cnt++; // 방문한 곳이 탈출했어!! 그럼 나도 탈출~
			flag[r][c] = flag[nr][nc]; // 방문한 곳이 탈출 못했어? 그럼 나도 못함. 너 했어? 그럼 나도 탈출함 ㅎ
			return flag[nr][nc]; // 내 전 방문지도!!!
		}
		
		flag[r][c] = dfs(nr, nc); // 나는 이동할 수 있으니.. 나의 탈출 여부는.. 다음 방문지에서 결정해준다...
		if (flag[r][c]) cnt++; // 에고고 나 탈출해따!!
		
		return flag[r][c];
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
