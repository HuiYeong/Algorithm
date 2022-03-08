package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B11724_연결요소의개수 {
	private static int N, M, cnt;
	private static boolean[] isSelect;
	private static boolean flag;
	private static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
		}
		cnt = 0;
		isSelect = new boolean[N+1];
		for (int i=1;i<=N;i++) {
			flag = false;
			dfs(i);
			if (flag) cnt++;
		}
		System.out.println(cnt);
	}
	
	private static void dfs(int i) {
		isSelect[i] = true;
		if (graph.get(i).size()==0) cnt++;
		for (int j=0;j<graph.get(i).size();j++) {
			if (!isSelect[graph.get(i).get(j)]) {
				flag = true;
				dfs(graph.get(i).get(j));
			}
		}
		return;
	}
}
