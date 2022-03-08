package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class B1260_DFS와BFS {
	private static int N, M;
	private static int[][] arr;
	private static Queue<Integer> bfs;
	private static Stack<Integer> dfs;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int M1 = Integer.parseInt(st.nextToken());
			int M2 = Integer.parseInt(st.nextToken());
			arr[M1][M2] = 1;
			arr[M2][M1] = 1;
		}
		// DFS
		dfs = new Stack<>();
		boolean[] isSelect = new boolean[N+1];
		dfsLoop(isSelect, V, 0);
		sb.append("\n");
		// BFS
		bfs = new ArrayDeque<>();
		isSelect = new boolean[N+1];
		int cnt = 0;
		while(true) {
			if(!isSelect[V]) sb.append(V+" ");
			isSelect[V] = true;
			for (int i=1;i<N+1;i++) {
				if (arr[V][i]==1 && !isSelect[i]) bfs.offer(i);
			}
			cnt++;
			
			if(!bfs.isEmpty()) V = bfs.poll();
			if (cnt == M) {
				if(!isSelect[V]) sb.append(V+" ");
				break;
			}
		}
		System.out.println(sb);
	}
	
	private static void dfsLoop(boolean[] isSelect, int V, int cnt) {
		if (cnt == M) {
			if(!isSelect[V]) sb.append(V+" ");
			return;
		}
		
		if (!isSelect[V]) sb.append(V+" ");
		isSelect[V] = true;
		for (int i=N;i>=1;i--) {
			if (arr[V][i]==1 && !isSelect[i]) dfs.push(i);
		}
		if (!dfs.isEmpty()) V = dfs.pop();
		dfsLoop(isSelect, V, cnt+1);
	}
}
