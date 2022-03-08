package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B1935_후위표기식2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String calc = br.readLine();
		Stack<String> stack = new Stack<>();
		int[] numbers = new int[N];
		for (int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i=0;i<calc.length();i++) {
			if (65<=calc.charAt(i) && calc.charAt(i)<=90) { // 피연산
				stack.push(numbers[calc.charAt(i)-65]+"");
			}
			else {
				double num = Double.parseDouble(stack.pop());
				double num2 = Double.parseDouble(stack.pop());
				if (calc.charAt(i)=='-') stack.push((num2-num)+"");
				else if (calc.charAt(i)=='*') stack.push((num2*num)+"");
				else if (calc.charAt(i)=='+') stack.push((num2+num)+"");
				else if (calc.charAt(i)=='/') stack.push((num2/num)+"");
			}
		}
		
		double Answer = Double.parseDouble(stack.pop());
		System.out.printf("%.2f",Answer);
	}
}
