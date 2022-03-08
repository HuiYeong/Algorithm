package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B4386_별자리만들기 {
	static double[][] star;
	static int N;
	static double MIN;
	static int[] roots;
	static PriorityQueue<Edge> edgeList;
	static class Edge implements Comparable<Edge>{
		int from, to;
		double weight;
		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return from+" "+to+" "+weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		star = new double[N][2];
		StringTokenizer st = null;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}
		// 입력완료
		edgeList = new PriorityQueue<>();
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (i==j) continue;
				double dis = distance(star[i][0], star[i][1], star[j][0], star[j][1]);
				edgeList.add(new Edge(i, j, dis));
			}
		}
		
		roots = new int[N];
		makeSet();
		
		int cnt = 0;
		double cost = 0;
		while(!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();
			if (union(edge.from, edge.to)) {
				cost+=edge.weight;
				if (++cnt == N-1) {
					System.out.println(cost);
					return;
				}
			}
		}
	}
	
	static double distance(double startX, double startY, double endX, double endY) {
		return Math.sqrt(Math.pow(Math.abs(endX-startX), 2) + Math.pow(Math.abs(endY-startY), 2));
	}
	
	static void makeSet() {
		for (int i=0;i<N;i++) {
			roots[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (roots[x]<0) return x;
		return roots[x] = findSet(roots[x]);
	}
	
	static boolean union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x==y) return false;
		roots[y] = x;
		return true;
	}
}
