package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B6137_문자열생성 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Character> word = new ArrayList<>(), list = new ArrayList<>();
		for (int i=0;i<N;i++) {
			word.add(br.readLine().charAt(0));
		}
		int front = 0, end = N-1;
		while(front<=end) {
			if (word.get(front)<word.get(end)) list.add(word.get(front++));
			else if (word.get(front)>word.get(end)) list.add(word.get(end--));
			else { // 앞, 뒤 문자가 같을 때
				int tempF = front+1, tempE = end-1, size = list.size();
				while(tempF<=tempE) {
					if (word.get(tempF)<word.get(tempE)) {
						list.add(word.get(front++));
						break;
					}
					else if (word.get(tempF)>word.get(tempE)) {
						list.add(word.get(end--));
						break;
					}
					else tempF++; tempE--;
				}
				if (size==list.size()) list.add(word.get(front++));
			}
		}
		for (int i=0;i<list.size();i++) {
			if (i!=0 && i%80==0) System.out.println();
			System.out.print(list.get(i));
		}
	}
}
