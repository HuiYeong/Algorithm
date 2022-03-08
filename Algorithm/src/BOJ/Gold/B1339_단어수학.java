package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class B1339_단어수학 {
	static int N, arr[], ans, MAX, idx, select[];
	static char src[];
	static boolean visit[];
	static List<Map<Character, Integer>> list;
	static List<String> numberList;
	static Set<Character> set;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		numberList = new ArrayList<>();
		set = new HashSet<>();
		
		for (int i=0;i<8;i++) {
			list.add(new HashMap<>());
		}
		for (int i=0;i<N;i++) {
			String input = br.readLine();
			numberList.add(input);
			for (int j=0;j<input.length();j++) {
				char now = input.charAt(j);
				if (!set.contains(now)) set.add(now);
			}
		}
		src = new char[set.size()];
		select = new int[set.size()];
		visit = new boolean[10];
		Iterator<Character> iter = set.iterator();
		while(iter.hasNext()) {
			src[idx++] = iter.next();
		}
		MAX = Integer.MIN_VALUE;
		permutation(0);
		System.out.println(MAX);
	}
	
	static void permutation(int cnt) {
		if (cnt == set.size()) {
			ans = 0;
			arr = new int[26];
			for (int i=0;i<src.length;i++) {
				arr[src[i]-'0'-17] = select[i];
			}
			search();
			return; 
		}
		
		for (int i=9;i>=0;i--) {
			if (visit[i]) continue;
			select[cnt] = i;
			visit[i] = true;
			permutation(cnt+1);
			visit[i] = false;
		}
	}
	
	static void search() {
		for (int i=0;i<numberList.size();i++) {
			String now = numberList.get(i);
			sb = new StringBuilder();
			for (int j=0;j<now.length();j++) {
				char nowChar = now.charAt(j);
				sb.append(arr[nowChar-'0'-17]);
			}
			ans += Integer.parseInt(sb.toString());
		}
		MAX = Math.max(MAX, ans);
	}
}
