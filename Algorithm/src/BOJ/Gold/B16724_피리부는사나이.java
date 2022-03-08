package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16724_피리부는사나이 {
	static int R, C, cnt, d, level, levelArr[][], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char arr[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		levelArr = new int[R][C];
		level = 1;
		for (int r=0;r<R;r++) {
			arr[r] = br.readLine().toCharArray();
		}
		
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (dfs(r, c, level++)) cnt++; // 방문을 하지 않았다면 방문 후 새로 생긴 경로만 cnt++
			}
		}
		System.out.println(cnt);
	}
	
	static boolean dfs(int r, int c, int level) {
		char now = arr[r][c];
		levelArr[r][c] = level;
		d = now=='U'?0:now=='D'?1:now=='L'?2:3;
		int nr = r + dir[d][0];
		int nc = c + dir[d][1];
		
		if (levelArr[nr][nc] != 0) { // 내가 갈 곳이 방문 되어있다면
			if (levelArr[nr][nc] == level) return true; // 현재 나의 레벨과 같다면 새로 생긴 경로 return true
			else return false; // 나의 레벨과 다르다면 기존에 있는 경로 return false
		}
		
		return dfs(nr, nc, level);
	}
}
