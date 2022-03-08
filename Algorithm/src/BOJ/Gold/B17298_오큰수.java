package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17298_오큰수 {
	static class nge {
		int i, value;

		public nge(int i, int value) {
			super();
			this.i = i;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[][] numbers = new int[N][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			numbers[i][0] = Integer.parseInt(st.nextToken());
		}
		
		Stack<nge> stack = new Stack<>();
		stack.push(new nge(0, numbers[0][0]));
		for (int i=1;i<N;i++) {
			while(!stack.isEmpty()) {
				if (stack.peek().value<numbers[i][0]) {
					nge n = stack.pop();
					numbers[n.i][1] = numbers[i][0];
				} else break;
			}
			stack.push(new nge(i, numbers[i][0]));
		}
		
		while(!stack.isEmpty()) {
			nge n = stack.pop();;
			numbers[n.i][1] = -1;
		}
		
		for (int i=0;i<N;i++) {
			sb.append(numbers[i][1]+" ");
		}
		System.out.println(sb);
	}
}
