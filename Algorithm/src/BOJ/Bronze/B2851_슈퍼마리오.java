package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2851_슈퍼마리오 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] mario = new int[10];
		int sum = 0, temp = 0;
		for (int i=0;i<10;i++) {
			mario[i] = Integer.parseInt(br.readLine());
			if (sum<100) {
				sum+=mario[i];
				temp = i;
			}
		}
		
		temp = sum-mario[temp];
		if (Math.abs(temp-100) == Math.abs(sum-100)) sb.append(sum);
		else if (Math.abs(temp-100) > Math.abs(sum-100)) sb.append(sum);
		else sb.append(temp);
		System.out.println(sb);
	}
}
