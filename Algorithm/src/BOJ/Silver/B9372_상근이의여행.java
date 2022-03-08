package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B9372_상근이의여행 {
	private static int N, M, cnt;
	private static ArrayList<ArrayList<Integer>> graph;
	private static boolean[] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
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
			// 입력완료
			check = new boolean[N+1];
			cnt = 0;
			check[1] = true;
			dfs(1);
			
			sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int i) {
		if (cnt==N-1) return;
		
		for (int j=0;j<graph.get(i).size();j++) {
			if (check[graph.get(i).get(j)]) continue;
			else {
				check[graph.get(i).get(j)] = true;
				cnt++;
				dfs(graph.get(i).get(j));
			}	
		}
	}
 }
