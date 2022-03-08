package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B2504_괄호의값 {
	private static Stack<String> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String par = br.readLine();
		stack = new Stack<>();
		for (int i=0;i<par.length()+1;i++) {
			if (check()) {
				int num = Integer.parseInt(stack.pop());
				if (check()) {
					int num2 = Integer.parseInt(stack.pop());
					stack.push((num+num2)+"");
				}
				else stack.push(num+"");
			}
			if (i == par.length()) break;
			char cur = par.charAt(i);
			if (cur == '(' || cur == '[') stack.push(cur+"");
			else if (!stack.isEmpty() && (cur == ')' || cur == ']')) {
				if ((cur == ')' && stack.peek().equals("(")) ||
						(cur == ']' && stack.peek().equals("["))) {
					stack.pop();
					stack.push(cur == ')'?"2":"3");
				}
				else if (check()) {
					int num = Integer.parseInt(stack.pop());
					if (!stack.isEmpty() && ((cur == ')' && stack.peek().equals("(")) ||
							(cur == ']' && stack.peek().equals("[")))) {
						stack.pop();
						stack.push(cur == ')'?(2*num)+"":(3*num)+"");
					}
				}
			}
		}
		int Answer = 0;
		if (stack.size() == 1 && 48<=stack.peek().charAt(0) && stack.peek().charAt(0)<=57) {
			Answer = Integer.parseInt(stack.pop());
		}
		System.out.println(Answer);
	}
	
	private static boolean check() {
		return !stack.isEmpty() && 48<=stack.peek().charAt(0) && stack.peek().charAt(0)<=57;
	}
}
