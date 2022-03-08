package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11497_통나무건너뛰기 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N = Integer.parseInt(br.readLine()); // 통나무 개수
			int[] log = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) {
				log[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(log);
			int[] calc = new int[N];
			int start = 0, end = N-1, index = 0;
			while(index<N-1) {
				int num1 = log[index++];
				int num2 = log[index++];
				calc[start++] = num1; calc[end--] = num2;
			}
			if (N%2==1) calc[start] = log[index];
			int MAX = Integer.MIN_VALUE;
			for (int i=0;i<N-1;i++) {
				MAX = Math.max(MAX, Math.abs(calc[i]-calc[i+1]));
			}
			sb.append(MAX+"\n");
		}
		System.out.println(sb);
	}
}
