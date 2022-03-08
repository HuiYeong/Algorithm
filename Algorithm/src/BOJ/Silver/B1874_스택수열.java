package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B1874_스택수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] sequence = new int[N];
		for (int i=0;i<N;i++) {
			sequence[i] = Integer.parseInt(br.readLine());
		}
		Stack<Integer> stack = new Stack<>();
		int cntS = 1, cntN = 0;
		while(true) {
			if (cntS == N+1 && stack.isEmpty()) break;
			if (!stack.isEmpty() && stack.peek() == sequence[cntN]) {
				stack.pop();
				sb.append("-\n");
				cntN++;
			}
			else if (cntS == N+1 && stack.peek() != sequence[cntN]) {
				break;
			}
			else {
				stack.push(cntS);
				cntS++;
				sb.append("+\n");
			}
		}
		
		if (stack.isEmpty()) System.out.println(sb);
		else System.out.println("NO");
	}
}

