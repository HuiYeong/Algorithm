package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4344_평균은넘겠지 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double N = Double.parseDouble(st.nextToken());
			double[] grade = new double[(int) N];
			int sum = 0;
			for (int i=0;i<N;i++) {
				grade[i] = Integer.parseInt(st.nextToken());
				sum+=grade[i];
			}
			double cnt = 0;
			for (int i=0;i<N;i++) {
				if (grade[i]>sum/N) cnt++;
			}
			System.out.printf("%.3f", ((double)(cnt/N))*100);
			System.out.println("%");
		}
	}
}
