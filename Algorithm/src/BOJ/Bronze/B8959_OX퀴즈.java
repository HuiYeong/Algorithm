package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B8959_OX퀴즈 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		for (int t=0;t<tc;t++) {
			String quiz = br.readLine();
			int cnt = 1, Answer = 0;
			for (int i=0;i<quiz.length();i++) {
				char temp = quiz.charAt(i);
				if (temp == 'O') stack.push(cnt++);
				else { // temp가 X면
					while(!stack.isEmpty()) {
						Answer+=stack.pop();
					}
					cnt=1;
				}
			}
			while(!stack.isEmpty()) {
				Answer+=stack.pop();
			}
			sb.append(Answer+"\n");
		}
		System.out.println(sb);
	}
}
