package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B5430_AC {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc=0;tc<T;tc++) {
			boolean front = true;
			boolean iserror = false;
			String p = br.readLine();
			int N = Integer.parseInt(br.readLine());
			Deque<Integer> deque = new ArrayDeque<>();
			String arrays = br.readLine();
			st = new StringTokenizer(arrays.substring(1, arrays.length()-1),",");
			while(st.hasMoreTokens()) {
				deque.offerLast(Integer.parseInt(st.nextToken()));
			}
			
			for (int i=0;i<p.length();i++) {
				if (p.charAt(i) == 'R') front = front==true?false:true;
				else if (p.charAt(i) == 'D') {
					if (deque.isEmpty()) {
						iserror = true;
						break;
					}
					else {
						if (front) deque.pollFirst();
						else deque.pollLast();
					}
				}
			}
			if (iserror) sb.append("error\n");
			else if (!deque.isEmpty()) {
				sb.append("[");
				while(deque.size()>1) {
					if (front) sb.append(deque.pollFirst()+",");
					else sb.append(deque.pollLast()+",");
				}
				if (front) sb.append(deque.pollFirst()+"]\n");
				else sb.append(deque.pollLast()+"]\n");
			}
			else sb.append("[]\n");
		}
		System.out.println(sb);
	}
}
