package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11723_집합의표현 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int key = 0;
		while(M-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			int num = 0;
			switch(order) {
				case "add": 
					num = Integer.parseInt(st.nextToken());
					key |= (1<<(num-1));
					break;
				case "remove":
					num = Integer.parseInt(st.nextToken());
					key = key & ~(1<<(num-1));
					break;
				case "check":
					num = Integer.parseInt(st.nextToken());
					sb.append((key & (1 << (num - 1))) != 0 ? "1\n" : "0\n");
					break;
				case "toggle":
					num = Integer.parseInt(st.nextToken());
					key ^= (1<<(num-1));
					break;
				case "all":
					key |= (~0);
					break;
				case "empty":
					key &= 0;
					break;
			}
		}
		System.out.println(sb.toString());
	}
}
