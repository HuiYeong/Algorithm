package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1259_팰린드롬수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int num = -1;
		while((num = Integer.parseInt(in.readLine()))!=0) {
			sb.append(num).reverse();
			if (Integer.parseInt(sb.toString()) == num)  sb2.append("yes\n");
			else sb2.append("no\n");
			sb.delete(0, sb.length());
		}
		System.out.println(sb2);
	}
}
