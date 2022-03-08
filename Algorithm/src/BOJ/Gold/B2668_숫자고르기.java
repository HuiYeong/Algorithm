package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

public class B2668_숫자고르기 {
	static boolean[] indexB, valueB;
	static int[] numbers;
	static int N;
	static String temp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		indexB = new boolean[N+1];
		valueB = new boolean[N+1];
		numbers = new int[N+1];
		TreeSet<Integer> tree = new TreeSet<>();
		for(int i=1;i<=N;i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		// 입력완료
		
		boolean flag = true;
		for (int i=1;i<=N;i++) {
			dfs(i);
			
			for (int j=1;j<=N;j++) {
				if (indexB[j]!=valueB[j]) {
					flag = false;
					break;
				}
			}
			
			valueB = new boolean[N+1];
			if(!flag) indexB = new boolean[N+1]; // 실패
			else { // 성공
				for (int j=1;j<=N;j++) {
					if (indexB[j]) tree.add(j);
				}
			}
			flag = true;
		}
		
		sb.append(tree.size()+"\n");
		Iterator<Integer> iter = tree.iterator();
		while(iter.hasNext()) {
			sb.append(iter.next()+"\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int index) {
		if (indexB[index]) return;
		
		indexB[index] = true;
		valueB[numbers[index]] = true;
		dfs(numbers[index]);
	}
}
