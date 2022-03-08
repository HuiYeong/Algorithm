package BOJ.Bronze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B2570_수정렬하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0;i<num;i++) {
			list.add(sc.nextInt());
		}
		
		Collections.sort(list);
		for (int x: list) System.out.println(x);
		sc.close();
	}
}
