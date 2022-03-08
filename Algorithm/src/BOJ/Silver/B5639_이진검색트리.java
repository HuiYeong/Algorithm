package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5639_이진검색트리 {
	private static StringBuilder sb;
	public static class Node {
		int value;
		Node left, right;
		public Node(int value) {
			this.value = value;
		}
		
		void insert(int n) {
			if (n<this.value) {
				if (this.left==null) this.left = new Node(n);
				else this.left.insert(n);
			} else {
				if (this.right==null) this.right = new Node(n);
				else this.right.insert(n);
			}
		}
	}
	
	private static void postOrder(Node node) {
		if (node == null) return;
		
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.value+"\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		Node root = new Node(Integer.parseInt(br.readLine()));
		
		String input = null;
		while((input=br.readLine())!=null) {
			if (input.equals("")) break;
			root.insert(Integer.parseInt(input));
		}
		
		postOrder(root);
		System.out.println(sb);
	}
}
