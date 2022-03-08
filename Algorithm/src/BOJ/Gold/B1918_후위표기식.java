package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B1918_후위표기식 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String sen = br.readLine(); // 중위 표기식
		Stack<Character> op = new Stack<>();
		
		for (int i=0;i<sen.length();i++) {
			char temp = sen.charAt(i);
			if (65<=temp && temp<=90) sb.append(temp);
			else {
				if (temp=='(') op.push(temp);
				else if (temp==')') {
					while(!op.isEmpty() && op.peek()!='(') {
						sb.append(op.pop());
					}
					op.pop();
				}
				else if (temp=='/' || temp=='*') {
					while(true) {
						if (!op.isEmpty() && (op.peek()=='/'||op.peek()=='*')) sb.append(op.pop());
						else break;
					}
					op.push(temp);
				}
				else if (temp=='+' || temp=='-') {
					while(true) {
						if (op.isEmpty() || op.peek()=='(') break;
						else sb.append(op.pop());
					}
					op.push(temp);
				}
			}
		}
		
		while(!op.empty()) {
			sb.append(op.pop());
		}
		
		System.out.println(sb);
	}
}
