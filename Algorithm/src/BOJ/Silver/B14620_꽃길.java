package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B14620_꽃길 {
	static int N, MIN;
	static int[][] map, deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static List<Flower> list;
	static int[] numbers;
	static class Flower {
		int r, c, money, indexC;

		public Flower(int r, int c, int money, int indexC) {
			super();
			this.r = r;
			this.c = c;
			this.money = money;
			this.indexC = indexC;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<>();
		StringTokenizer st = null;
		int index = 0;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				list.add(new Flower(i, j, map[i][j], index++));
			}
		}
		
		numbers = new int[3];
		MIN = Integer.MAX_VALUE;
		combination(0, 0);
		System.out.println(MIN);
	}
	
	static void combination(int cnt, int start) {
		if (cnt==3) {
			visited = new boolean[N][N];
			check();
			return;
		}
		
		for (int i=start;i<list.size();i++) {
			numbers[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
	
	static void check() {
		int moneySum = 0;
		for (int i=0;i<numbers.length;i++) {
			Flower now = list.get(numbers[i]);
			moneySum += map[now.r][now.c];
			visited[now.r][now.c] = true;
			for (int d=0;d<4;d++) {
				int nr = now.r+deltas[d][0];
				int nc = now.c+deltas[d][1];
				if (isIn(nr, nc) && !visited[nr][nc]){
					visited[nr][nc] = true;
					moneySum+= map[nr][nc];
				} else return;
				
				if (moneySum > MIN) return;
			}
		}
		MIN = Math.min(MIN, moneySum);
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
