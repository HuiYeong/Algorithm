package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class B1764_듣보잡 {
	static String[] l, s;
	static List<String> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		l = new String[N];
		s = new String[M];
		HashMap<String, Integer> hash = new HashMap<>();
		for (int i=0;i<N;i++) {
			l[i] = br.readLine();
		}
		for (int j=0;j<M;j++) {
			s[j] = br.readLine();
			hash.put(s[j], 0);
		}
		list = new ArrayList<>();
		Arrays.sort(s);
		for (int i=0;i<N;i++) {
			if(hash.containsKey(l[i])) list.add(l[i]);
		}
		Collections.sort(list);
		sb.append(list.size()+"\n");
		for (int i=0;i<list.size();i++) {
			sb.append(list.get(i)+"\n");
		}
		System.out.println(sb);
	}
}