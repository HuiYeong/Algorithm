package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * < 자료구조 문제 >
 * 
 * 스택과 큐를 이용하여 풀었다. 
 * 먼저 큐에 모든 값을 넣은 후 하나씩 꺼내줬다.
 * 순서에 맞지 않은 경우 스택을 확인해 스택에 있는 값이 순서에 맞다면 값을 꺼내어주고 없다면 큐에 있는 값을 스택에 집어넣어 준다.
 * 
 * 큐에서 모든 사람을 빼내었다면 스택만 확인해준다.
 * 스택을 확인해 순서에 맞지 않는다면 순서를 지킬 수 없으므로 Sad 출력, 순서에 맞다면 계속 확인해 순서를 모두 찾으면 Nice 출력
 */
public class B12789_도키도키간식드리미 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		Queue<Integer> queue = new LinkedList<>();
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) queue.offer(Integer.parseInt(st.nextToken()));
		
		int cnt = 1;
		boolean success = false;
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
				if (stack.peek() != cnt) break stop;
				else if (stack.peek() == cnt) {
					stack.pop();
					cnt++;
				}
			}
			
			if (cnt > N) {
				success = true;
				break stop;
			}
		}
		
		if (success) System.out.println("Nice");
		else System.out.println("Sad");
	}
}
