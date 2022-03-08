package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1546_평균 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[] grade = new double[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		double MAX = Double.MIN_VALUE;
		for (int i=0;i<N;i++) {
			grade[i] = Integer.parseInt(st.nextToken());
			MAX = Math.max(MAX, grade[i]);
		}
		double sum = 0;
		for (int i=0;i<N;i++) {
			grade[i] = (grade[i]*100/MAX);
			sum+=grade[i];
		}
		
		double avg = sum/N;
		System.out.println(avg);
	}
}
