package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B2580_스토쿠 {
	static int map[][] = new int[9][9];
	static List<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		list = new ArrayList<>();
		for (int r=0;r<9;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<9;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0) list.add(new int[] {r, c});
			}
		}
		
		dfs(0);
	}
	
	static void dfs(int cnt) {
		if (cnt >= list.size()) {
			StringBuilder sb = new StringBuilder();
			for (int r=0;r<9;r++) {
				for (int c=0;c<9;c++) {
					sb.append(map[r][c]+" ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		int[] now = list.get(cnt);
		for (int i=1;i<=9;i++) {
			if (check(now, i)) {
				map[now[0]][now[1]] = i;
				dfs(cnt+1);
			};
		}
		map[now[0]][now[1]] = 0;
		return;
	}
	
	static boolean check(int[] now, int value) {
		// 가로 검사
		for (int c=0;c<9;c++) {
			if (map[now[0]][c] == value) return false; 
		}
		
		// 세로 검사
		for (int r=0;r<9;r++) {
			if (map[r][now[1]] == value) return false;
		}
		
		// 3x3 검사
		int tr = (now[0]/3)*3;
		int tc = (now[1]/3)*3;
		for (int r=tr;r<tr+3;r++) {
			for (int c=tc;c<tc+3;c++) {
				if (map[r][c] == value) return false;
			}
		}
		return true;
	}
}
