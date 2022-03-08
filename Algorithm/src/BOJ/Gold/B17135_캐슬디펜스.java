package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B17135_캐슬디펜스 {
	private static int N, M, D, max;
	private static int[][] map;
	private static int[] numbers;
	
	public static class list implements Comparable<list>{
		int r, c, d;
		boolean isTarget;

		public list(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		public boolean attack(int r, int c) {
			return r+c<=D;
		}

		@Override
		public String toString() {
			return this.r+" "+this.c+" "+this.d;
		}

		@Override
		public int compareTo(list o) {
			if (this.d == o.d) return Integer.compare(this.c, o.c);
			return Integer.compare(this.d, o.d);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = Integer.MIN_VALUE;
		numbers = new int[3]; // 궁수 뽑을거
		combination(0, 0);
		System.out.println(max);
	}
	
	private static void combination(int cnt, int start) {
		if (cnt == 3) {
			defense(numbers);
			return;
		}
		
		for (int i=start;i<M;i++) {
			numbers[cnt] = i;
			numbers[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
	
	private static void defense(int[] position) {
		ArrayList<list> enemy = new ArrayList<>();
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (map[i][j]==1) enemy.add(new list(i, j));
			}
		}
		
		int kill = 0;
		while(true) {
			for (int i=0;i<3;i++) {
				PriorityQueue<list> targetE = new PriorityQueue<>();
				int archer = position[i];
				for (int j=0;j<enemy.size();j++) {
					list enemies = enemy.get(j);
					enemies.d = Math.abs(enemy.get(j).r-N)+Math.abs(enemy.get(j).c-archer);
					if (enemies.d<=D) targetE.add(enemies);
				}
				if (!targetE.isEmpty()) {
					targetE.poll().isTarget = true;
				}
			}
			
			for (int i=0;i<enemy.size();i++) {
				list enemies = enemy.get(i);
				if (enemies.isTarget) {
					enemy.remove(i--);
					kill++;
				}
				else {
					if(enemies.r ==N-1) {
						enemy.remove(i--);
					}
					// 아직 끝이 아니면.. 한칸 아래로 이동.
					else {
						enemies.r++;
					}
				}
			}
			if (enemy.size()==0) break;
		}
		max = Math.max(max, kill);
	}
}
