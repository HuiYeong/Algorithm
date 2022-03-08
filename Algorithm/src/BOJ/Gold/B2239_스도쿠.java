package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class B2239_스도쿠 {
	static int[][] map, matrixMap;
	static StringBuilder sb;
	static List<Node> list;
	static class Node {
		int r, c, num;

		public Node(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", num=" + num + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		matrixMap = new int[9][9];
		List<HashMap<Integer, Integer>> row = new ArrayList<>();
		List<HashMap<Integer, Integer>> col = new ArrayList<>();
		List<HashMap<Integer, Integer>> matrix = new ArrayList<>();
		list = new ArrayList<>();
		for (int r=0;r<9;r++) {
			String input = br.readLine();
			row.add(new  HashMap<>());
			col.add(new  HashMap<>());
			matrix.add(new HashMap<>());
			for (int c=0;c<9;c++) {
				map[r][c] = input.charAt(c)-'0';
				if (map[r][c]==0) list.add(new Node(r, c, 0));
			}
		}
		// 입력완료
		
		for (int r=0;r<9;r++) { 
			for (int c=0;c<9;c++) {
				if(map[r][c]!=0) row.get(r).put(map[r][c], 0);
			}
		}
		
		for (int c=0;c<9;c++) {
			for (int r=0;r<9;r++) {
				if (map[r][c]!=0) col.get(c).put(map[r][c], 0);
			}
		}
		
		int index = 0;
		for (int r=0;r<9;r+=3) {
			for (int c=0;c<9;c+=3) {
				getMatrix(matrix, r, c, index++);
			}
		}
		
		check(0, matrix, row, col);
	}
	
	static void getMatrix(List<HashMap<Integer, Integer>> matrix, int sR, int sC, int index) {
		for (int r=sR;r<sR+3;r++) {
			for (int c=sC;c<sC+3;c++) {
				if (map[r][c]!=0) matrix.get(index).put(map[r][c], 0);
				matrixMap[r][c] = index;
			}
		}
	}
	
	static void check(int idx, List<HashMap<Integer, Integer>> matrix, List<HashMap<Integer, Integer>> row, List<HashMap<Integer, Integer>> col) {
		Node now = null;
//		System.out.println(list.size()+"/"+idx);
		if (idx == list.size()) {
			int index = 0;
			for (int i=0;i<9;i++) {
				for (int j=0;j<9;j++) {
					if (map[i][j]==0) sb.append(list.get(index++).num);
					else sb.append(map[i][j]);
				} sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		else now = list.get(idx);
		
		for (int i=1;i<10;i++) {
			if (!matrix.get(matrixMap[now.r][now.c]).containsKey(i) && !row.get(now.r).containsKey(i) && !col.get(now.c).containsKey(i)) {
				matrix.get(matrixMap[now.r][now.c]).put(i, 0);
				row.get(now.r).put(i, 0);
				col.get(now.c).put(i, 0);
				now.num = i;
				check(idx+1, matrix, row, col);
				matrix.get(matrixMap[now.r][now.c]).remove(i);
				row.get(now.r).remove(i);
				col.get(now.c).remove(i);
				now.num = 0;
			} 
		}
		return;
	}
}
