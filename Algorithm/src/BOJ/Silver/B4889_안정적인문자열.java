package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B4889_안정적인문자열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 1;
		while(true) {
			String word = br.readLine();
			if (word.contains("-")) break;
			Stack<Character> stack = new Stack<>();
			int Answer = 0;
			for (int i=0;i<word.length();i++) {
				if (word.charAt(i)=='{') stack.push(word.charAt(i));
				else {
					if (stack.isEmpty()) {
						stack.push('{');
						Answer++;
					}
					else if (word.charAt(i)=='}' && stack.peek()=='{') stack.pop();
				}
			}
			
			sb.append(cnt+". "+(Answer+stack.size()/2)+"\n");
			cnt++;
		}
		System.out.println(sb);
	}
}