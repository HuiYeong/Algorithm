package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B3052_나머지 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0;i<10;i++) {
			boolean flag = true;
			int num = Integer.parseInt(br.readLine());
			for (int j=0;j<list.size();j++) {
				if (list.get(j)==num%42) flag = false; 
			}
			if (flag) list.add(num%42);
		}
		System.out.println(list.size());
	}
}
