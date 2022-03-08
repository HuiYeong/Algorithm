package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2562_최댓값 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] num = new int[10][2];
		for (int i=1;i<=9;i++) {
			num[i][0] = Integer.parseInt(br.readLine());
			num[i][1] = i;
		}
		
		Arrays.sort(num, (o1, o2)-> {
			return Integer.compare(o1[0], o2[0])*-1;
		});
		
		System.out.println(num[0][0]+"\n"+num[0][1]);
	}
}
