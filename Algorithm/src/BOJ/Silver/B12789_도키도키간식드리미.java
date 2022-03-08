package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class B12789_도키도키간식드리미 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		Queue<Integer> queue = new LinkedList<>();
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i=0;i<N;i++) queue.offer(Integer.parseInt(st.nextToken()));
		
		int cnt = 1;
		boolean success = true;
		stop:while(true) {
			if (!queue.isEmpty() && queue.peek() != cnt) {
				if (!stack.isEmpty() && stack.peek() == cnt) {
					stack.pop();
					cnt++;
				}
				else stack.push(queue.poll());
			}
			else if (!queue.isEmpty() && queue.peek() == cnt){
				queue.poll();
				cnt++;
			}
			
			if (queue.isEmpty() && !stack.isEmpty()) {
				if (stack.peek() != cnt) {
					success = false;
					break stop;
				}
				else if (stack.peek() == cnt) {
					stack.pop();
					cnt++;
				}
			}
			
			if (cnt > N || stack.isEmpty()) break stop;
		}
		
		if (success) System.out.println("Nice");
		else System.out.println("Sad");
	}
}
