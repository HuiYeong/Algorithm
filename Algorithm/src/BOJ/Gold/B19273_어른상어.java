package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B19273_어른상어 {
	static int N, K;
	static int[][] map, deltas = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static List<Shark> sharks, smells;
	static class Shark implements Comparable<Shark>{
		int r, c, d, num, smell;
		List<Integer> priority;
		public Shark(int r, int c, int d, int num, int smell, List<Integer> priority) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.num = num;
			this.smell = smell;
			this.priority = priority;
		}
		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", d="+d+", num=" + num + ", smell="+ smell+", priority=" + priority + "]";
		}
		@Override
		public int compareTo(Shark o) {
			return Integer.compare(this.num, o.num);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		sharks = new ArrayList<>();
		smells = new ArrayList<>();
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c]>0) sharks.add(new Shark(r, c, 0, map[r][c], K, new ArrayList<>()));
			}
		}
		Collections.sort(sharks);
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<M;i++) {
			sharks.get(i).d = Integer.parseInt(st.nextToken());
		}
	}
}
