package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2891_카약과강풍 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[] damage = new int[S];
		int[] more = new int[R];
		st = new StringTokenizer(br.readLine()," ");
		for (int i=0;i<S;i++) {
			damage[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine()," ");
		for (int i=0;i<R;i++) {
			more[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(damage);
		Arrays.sort(more);
		boolean success = false;
		int Answer = 0;
		for (int i=0;i<S;i++) {
			success = false;
			for (int j=0;j<R;j++) {
				if (more[j]!=0 && (damage[i] == more[j] || damage[i] == more[j]-1
						|| damage[i] == more[j]+1)) {
					success = true;
					more[j] = 0;
					break;
				}
			}
			if (!success) Answer++;
		}
		System.out.println(Answer);
	}
}
