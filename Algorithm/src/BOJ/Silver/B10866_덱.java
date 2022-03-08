package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B10866_덱 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		Deque<Integer> q = new LinkedList<>();
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			String command = st.nextToken();
			int value = 0;
			if (command.contains("push")) {
				value = Integer.parseInt(st.nextToken());
				if (command.contains("f")) q.offerFirst(value);
				else q.offerLast(value);
			}
			else if (command.contains("pop") || command.equals("front") || command.equals("back") ||
					command.equals("empty")) {
				if (q.isEmpty()) {
					if (command.contains("e")) sb.append(1+"\n");
					else sb.append(-1+"\n");
				}
				else {
					if (command.contains("pop")) {
						if (command.contains("f")) sb.append(q.pollFirst()+"\n");
						else sb.append(q.pollLast()+"\n");
					}
					else if (command.equals("front")) sb.append(q.peekFirst()+"\n");
					else if (command.equals("back")) sb.append(q.peekLast()+"\n");
					else if (command.contains("e")) sb.append(0+"\n");
				}
			}
			else if (command.equals("size")) sb.append(q.size()+"\n");
		}
		System.out.println(sb);
	} 
}
