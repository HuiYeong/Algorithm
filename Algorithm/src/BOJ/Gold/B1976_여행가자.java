package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B1976_여행가자 {
	static int N, M;
	static int[] parents;
	static void makeSet() {
		for (int i=1;i<=N;i++) {
			parents[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (parents[x]<0) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return;
		parents[bRoot] = aRoot;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		M = Integer.parseInt(br.readLine());
		makeSet();
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<=N;j++) {
				int to = Integer.parseInt(st.nextToken());
				if (to==1) union(i, j);
			}
		}
		boolean flag = true;
		st = new StringTokenizer(br.readLine());
		List<Integer> list = new ArrayList<>();
		while(st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		for(int i=1;i<list.size();i++) {
			if (findSet(list.get(0))!=findSet(list.get(i))) {
				flag = false;
				break;
			}
		}
		
		if (flag) System.out.println("YES");
		else System.out.println("NO");
	}
}
