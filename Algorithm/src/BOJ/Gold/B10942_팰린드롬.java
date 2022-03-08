package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10942_팰린드롬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		StringBuilder ans = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			sb.append(Integer.parseInt(st.nextToken()));
		}
		int M = Integer.parseInt(br.readLine());
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			String compare = sb.substring(S-1, E);
			temp.append(compare);
			String compare2 = temp.reverse().toString();
			if (compare.equals(compare2)) ans.append(1+"\n");
			else ans.append(0+"\n");
			temp.delete(0, temp.length());
		}
		System.out.println(ans);
	}
}
