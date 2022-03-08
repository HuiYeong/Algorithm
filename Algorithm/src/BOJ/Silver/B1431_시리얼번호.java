package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B1431_시리얼번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String[][] serial = new String[N][2];
		int sum = 0;
		for (int i=0;i<N;i++) {
			sum = 0;
			serial[i][0] = br.readLine();
			for (int j=0;j<serial[i][0].length();j++) {
				if (48<=serial[i][0].charAt(j) && serial[i][0].charAt(j) <= 58) {
					sum+= serial[i][0].charAt(j)-'0';
				}
			}
			serial[i][1] = sum+"";
		}
		Arrays.sort(serial,(o1, o2) -> {
			if (o1[0].length()==o2[0].length()) {
				if (Integer.parseInt(o1[1])==Integer.parseInt(o2[1])) {
					return o1[0].compareTo(o2[0]);
				}
				return Integer.parseInt(o1[1])-Integer.parseInt(o2[1]);
			}
			return o1[0].length()-o2[0].length();
		});
		
		for (int i=0;i<N;i++) {
			sb.append(serial[i][0]+"\n");
		}
		System.out.println(sb);
	}
}