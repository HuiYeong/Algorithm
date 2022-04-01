package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * < 스택 문제 >
 * 
 * 입력 받는 값을 하나씩 스택에 넣다가 닫히는 괄호 발견 시 값을 계산한다.
 * 입력이 끝날 때까지 반복하여 최종 값을 도출해낸다.
 */
public class B2504_괄호의값 {
	private static Stack<String> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String par = br.readLine();
		stack = new Stack<>();
		stack.push(par.charAt(0)+"");
		for (int i=1;i<par.length()+1;i++) {
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
