package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2920_음계 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] num = new int[9];
		for (int i=1;i<=8;i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		int ans = 0;
		if (num[1]<num[2]) ans = 1;
		else ans = 2;
		for (int i=3;i<=8;i++) {
			if (num[i-1]<num[i] && ans == 1) ans = 1;
			else if (num[i-1]>num[i] && ans==2) ans = 2;
			else ans = 3;
		}
		
		if (ans==1) System.out.println("ascending");
		else if (ans==2) System.out.println("descending");
		else System.out.println("mixed");
	}
}
