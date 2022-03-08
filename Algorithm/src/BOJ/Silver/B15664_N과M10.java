package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B15664_N과M10 {
	static int N, M;
	static int[] select, src;
	static Set<String> set;
	static StringBuilder sb, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		src = new int[N];
		select = new int[M];
		set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			src[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(src);
		sb = new StringBuilder();
		ans = new StringBuilder();
		combination(0, 0);
		
		System.out.println(ans);
	}
	
	static void combination(int cnt, int startIdx) {
		if (cnt == M) {
			Arrays.sort(select);
			for (int i=0;i<select.length;i++) {
				sb.append(select[i]);
				if (i!=select.length-1) sb.append(" ");
			}
			String value = sb.toString();
			if (!set.contains(value))
				ans.append(value+"\n");
				set.add(value);
			
			sb.delete(0, sb.length());
			return;
		}
		for (int i=startIdx;i<src.length;i++) {
        	select[cnt] = src[i];
        	combination(cnt+1, i+1);
        }
	}
}
