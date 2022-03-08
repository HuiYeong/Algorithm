package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17952_과제는끝나지않아 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Stack<int[]> stack = new Stack<>();
		int Answer = 0;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int work = Integer.parseInt(st.nextToken());
			if (work == 1) {
				int A = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				T -= 1;
				if (T == 0) Answer+=A;
				else stack.push(new int[] {A, T});
			}
			else {
				int[] temp = new int[2];
				if (!stack.isEmpty()) {
					temp = stack.pop();
					temp[1]--;
					if (temp[1] == 0) Answer+=temp[0];
					else stack.push(temp);
				}
			}
		}
		System.out.println(Answer);
	}
}
