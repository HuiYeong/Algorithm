package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class B17471_게리맨더링 {
	static int N, MIN;
	static int[] population;
	static boolean[] isSelect, visited;
	static List<TreeSet<Integer>> list;
	static List<Integer> group1, group2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		isSelect = new boolean[N+1];
		list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<=N;i++) {
			list.add(new TreeSet<>());
			if (i!=0) population[i] = Integer.parseInt(st.nextToken());
		}
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j=0;j<n;j++) {
				list.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		MIN = Integer.MAX_VALUE;
		powerSet(1);
		if (MIN == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(MIN);
	}
	
	static void powerSet(int cnt) {
		if (cnt==N+1) {
			group1 = new ArrayList<>();
			group2 = new ArrayList<>();
			for (int i=1;i<=N;i++) {
				if (isSelect[i]) group1.add(i);
				else group2.add(i);
			}
			if (group1.size()==0 || group2.size()==0) return;
			visited = new boolean[N+1]; 
			checkConnect(group1.get(0), group1);
			for (int i = 1; i <= N; i++) {
	            if (isSelect[i] && !visited[i]) { // 선택된건데 , group1 인데 연결이 안되어있으면
	               return;
	            }
	         }
			
			visited = new boolean[N+1];
			checkConnect(group2.get(0), group2);
			for (int i = 1; i <= N; i++) {
	            if (!isSelect[i] && !visited[i]) { // 선택된건데 , group1 인데 연결이 안되어있으면
	               return;
	            }
	         }
			int sum1 = 0, sum2 = 0;
			for (int i=1;i<=N;i++) {
				if (isSelect[i]) sum1+=population[i];
				else sum2+=population[i];
			}
			MIN = Math.min(MIN, Math.abs(sum1-sum2));
			return;
		}
		
		isSelect[cnt] = true;
		powerSet(cnt+1);
		isSelect[cnt] = false;
		powerSet(cnt+1);
	}
	
	static void checkConnect(int a, List<Integer> group) {
		visited[a] = true;
		boolean contain = false;
		if (group.contains(a)) contain = true;
			
		for (int i=1;i<=N;i++) {
			if (i==a) continue;
			if (contain && list.get(i).contains(a) && !visited[i]) {
				checkConnect(i, group);
			}
	   }

	}
}
