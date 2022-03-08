package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1991_트리순회 {
	private static char[][] tree;
	private static StringBuilder sb;
	private static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		tree = new char[N][3];
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			tree[i][0] = st.nextToken().charAt(0);
			tree[i][1] = st.nextToken().charAt(0);
			tree[i][2] = st.nextToken().charAt(0);
		}
		
		preOrder('A');
		sb.append("\n");
		inOrder('A');
		sb.append("\n");
		lastOrder('A');
		System.out.println(sb);
	}
	
	private static void preOrder(char current) {
		int cnt = -1;
		for (int i=0;i<N;i++) {
			if (tree[i][0] == current) {
				cnt = i;
				break;
			}
		}
		if (cnt==-1) return;
		
		sb.append(tree[cnt][0]);
		preOrder(tree[cnt][1]);
		preOrder(tree[cnt][2]);
	}
	
	private static void inOrder(char current) {
		int cnt = -1;
		for (int i=0;i<N;i++) {
			if (tree[i][0] == current) {
				cnt = i;
				break;
			}
		}
		if (cnt==-1) return;
		
		inOrder(tree[cnt][1]);
		sb.append(tree[cnt][0]);
		inOrder(tree[cnt][2]);
	}
	
	private static void lastOrder(char current) {
		int cnt = -1;
		for (int i=0;i<N;i++) {
			if (tree[i][0] == current) {
				cnt = i;
				break;
			}
		}
		if (cnt==-1) return;
		
		lastOrder(tree[cnt][1]);
		lastOrder(tree[cnt][2]);
		sb.append(tree[cnt][0]);
	}
}
