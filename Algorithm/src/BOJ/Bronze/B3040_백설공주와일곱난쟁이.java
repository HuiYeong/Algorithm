package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B3040_백설공주와일곱난쟁이 {
	private static int[] dwarf;
	private static int[] numbers;
	private static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		dwarf = new int[9];
		for (int i=0;i<9;i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
		}
		numbers = new int[7];
		combination(0, 0);
	}
	
	private static void combination(int cnt, int start) {
		if (cnt==7) {
			int sum = 0;
			for (int i=0;i<7;i++) {
				sum+=numbers[i];
			}
			
			if (sum == 100) {
				Arrays.sort(numbers);
				for (int i=0;i<7;i++) {
					sb.append(numbers[i]+"\n");
				}
				System.out.println(sb);
				System.exit(0);
			}
			return;
		}
		
		for (int i=start;i<dwarf.length;i++) {
			numbers[cnt] = dwarf[i];
			combination(cnt+1, i+1);
		}
	}
}
