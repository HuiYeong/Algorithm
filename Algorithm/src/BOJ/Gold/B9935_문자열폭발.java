package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B9935_문자열폭발 {
	static String input, search;
	static Stack<Character> stack, temp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		input = br.readLine();
		search = br.readLine();
		stack = new Stack<>();
		temp = new Stack<>();
		for (int i=0;i<input.length();i++) {
			stack.add(input.charAt(i));
			if (temp.size()>0) temp.clear();
			if (stack.peek()==search.charAt(search.length()-1) && stack.size()>=search.length()){
				for (int j=search.length()-1;j>=0;j--) {
					char now = stack.pop();
					temp.add(now);
					if (now!=search.charAt(j)) {
						while(!temp.isEmpty()) {
							stack.add(temp.pop());
						}
					}
				}
			}
		}
		if (stack.size()>0) {
			while(!stack.isEmpty()) sb.append(stack.pop());
			System.out.println(sb.reverse());
		} else System.out.println("FRULA");
	}
}